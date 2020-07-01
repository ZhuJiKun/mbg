package com.bmfm.mdg.out;

import com.bmfm.mdg.config.Config;
import com.bmfm.mdg.entity.ColumnEntity;
import com.bmfm.mdg.entity.TableEntity;
import com.bmfm.mdg.utils.DateUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

/**
 * @author jikun.zhu
 * @date 2020/7/1 4:44 下午
 */
public class BeanOut implements Out {

    @Override
    public void output(TableEntity table) throws Exception {
        File file = new File(Config.getOutPath() + "/" + table.getName() + ".java");
        if (!file.exists())  {
            file.createNewFile();
        }


        StringBuilder sb = new StringBuilder(1024);
        sb.append("package ").append(Config.getDOPackagePath()).append(";").append(NEXT_LINE).append(NEXT_LINE);
        if (table.isHasDate()) {
            sb.append("import java.util.Date;").append(NEXT_LINE).append(NEXT_LINE);
        }
        sb.append("/**").append(NEXT_LINE).append(" * ").append(NEXT_LINE).append(" * @date ").append(DateUtil.format(new Date()))
                .append(NEXT_LINE).append(" */").append(NEXT_LINE);

        // 正文开始
        sb.append("public class ").append(table.getName()).append(" {").append(NEXT_LINE).append(NEXT_LINE);
        for (ColumnEntity column : table.getColumns()) {
            if (column.getDesc() != null && column.getDesc().length() > 0) {
                sb.append("    /**").append(NEXT_LINE).append("     * ").append(column.getDesc()).append(NEXT_LINE)
                        .append("     */").append(NEXT_LINE);
            }
            sb.append("    private ").append(column.getJavaType()).append(" ").append(column.getProperty()).append(";").append(NEXT_LINE).append(NEXT_LINE);
        }
        sb.append("}");

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }
}
