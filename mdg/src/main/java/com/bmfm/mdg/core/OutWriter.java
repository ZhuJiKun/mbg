package com.bmfm.mdg.core;

import com.bmfm.mdg.config.Config;
import com.bmfm.mdg.core.out.BeanOut;
import com.bmfm.mdg.core.out.InsertSeletiveMapperOut;
import com.bmfm.mdg.entity.ColumnEntity;
import com.bmfm.mdg.entity.MyStringBuilder;
import com.bmfm.mdg.entity.TableEntity;
import com.bmfm.mdg.core.out.InsertMapperOut;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jikun.zhu
 * @date 2020/7/1 4:49 下午
 */
public class OutWriter {
    public final static String NEXT_LINE = "\r\n";

    private final static List<MapperOut> list = new ArrayList<>();
    static {
        list.add(new InsertMapperOut());
        list.add(new InsertSeletiveMapperOut());
    }


    private TableEntity table;
    BeanOut beanOut = new BeanOut();

    public OutWriter(TableEntity table) {
        this.table = table;
    }

    public void out() throws Exception {
        beanOut.output(table);
        outDAO();
        outMapper();
    }

    private void outDAO() throws Exception {
        File file = new File(Config.getOutPath() + "/" + table.getName() + "Dao.java");
        if (!file.exists())  {
            file.createNewFile();
        }

        MyStringBuilder sb = new MyStringBuilder(1024);
        sb.append("package ").append(Config.getDaoPackagePath()).append(";").nextLine(2);
        sb.append("import ").append(Config.getDOPackagePath()).append(".").append(table.getName()).append(";").nextLine(2);

        sb.appendPre(beanOut.notes(table.getDesc()));

        sb.append("public interface ").append(table.getName()).append("Dao").append(" {").nextLine(2);

        for (MapperOut out : list) {
            sb.append(out.outDao(table)).nextLine(2);
        }

        sb.appendPre("}");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }



    private void outMapper() throws Exception {
        File file = new File(Config.getOutPath() + "/" + table.getName() + "Mapper.xml");
        if (!file.exists())  {
            file.createNewFile();
        }

        MyStringBuilder sb = new MyStringBuilder(1024);
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").nextLine();
        sb.append("<mapper namespace=\"").append(Config.getDaoPackagePath()).append(table.getName()).append("Dao\">").nextLine(2);

        // 构建映射关系
        sb.append("  <resultMap id=\"BaseResultMap\" type=\"").append(Config.getDOPackagePath()).append(table.getName()).appendNext("\">");
        // 暂时不考虑id的问题
        sb.appendNext("    <id column=\"id\" jdbcType=\"BIGINT\" property=\"id\" />");
        for (ColumnEntity column : table.getColumns()) {
            if ("id".equals(column.getProperty())) {
                continue;
            }
            sb.append("    <result column=\"").append(column.getColumn()).append("\" jdbcType=\"").append(column.getJdbcType()).append("\" property=\"").append(column.getProperty()).appendNext("\" />");;
        }
        sb.append("  </resultMap>").nextLine(2);

        sb.append("  <sql id=\"Base_Column_List\">");

        int i = 0;
        for (ColumnEntity column : table.getColumns()) {
            if (i % 5 == 0) {
                sb.appendPre("    ");
            }
            sb.append(column.getColumn());
            if (i != table.getColumns().size() - 1) {
                sb.append(",");
            }
            i++;
        }
        sb.appendPre("  </resultMap>").nextLine(2);

        for (MapperOut out : list) {
            sb.append(out.outMapper(table)).nextLine(2);
        }

        sb.appendPre("</mapper>");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

}
