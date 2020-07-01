package com.bmfm.mdg.core.out;

import com.bmfm.mdg.config.Config;
import com.bmfm.mdg.core.Out;
import com.bmfm.mdg.entity.ColumnEntity;
import com.bmfm.mdg.entity.MyStringBuilder;
import com.bmfm.mdg.entity.TableEntity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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

        MyStringBuilder sb = new MyStringBuilder(1024);
        sb.append("package ").append(Config.getDOPackagePath()).append(";").nextLine(2);
        if (table.isHasDate()) {
            sb.append("import java.util.Date;").nextLine(2);
        }
        sb.append(notes(table.getDesc()));

        // 正文开始
        sb.append("public class ").append(table.getName()).append(" {").nextLine(2);
        for (ColumnEntity column : table.getColumns()) {
            if (column.getDesc() != null && column.getDesc().length() > 0) {
                sb.append("    /**").appendPre("     * ").appendNext(column.getDesc())
                        .appendNext("     */");
            }
            sb.append("    private ").append(column.getJavaType()).append(" ").append(column.getProperty()).append(";").nextLine(2);
        }
        sb.append("}");

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }
}
