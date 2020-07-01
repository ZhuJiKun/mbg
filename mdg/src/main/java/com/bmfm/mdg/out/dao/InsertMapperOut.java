package com.bmfm.mdg.out.dao;

import com.bmfm.mdg.config.Config;
import com.bmfm.mdg.entity.ColumnEntity;
import com.bmfm.mdg.entity.TableEntity;
import com.bmfm.mdg.out.MapperOut;

/**
 * @author jikun.zhu
 * @date 2020/7/1 5:00 下午
 */
public class InsertMapperOut extends MapperOut {

    @Override
    public String outDao(TableEntity table) {
        return "    int insert(" + table.getName() + " record);";
    }

    @Override
    public String outMapper(TableEntity table) {
        StringBuilder sb = new StringBuilder(128);
        sb.append("  <insert id=\"insert\" parameterType=\"").append(Config.getDOPackagePath()).append(".").append(table.getName()).append("\">").append(NEXT_LINE);
        sb.append("    insert into ").append(table.getTableName()).append(" (");
        for (int i = 0; i < table.getColumns().size(); i++) {
            ColumnEntity column = table.getColumns().get(i);
            if (i % 3 == 0) {
                sb.append(NEXT_LINE).append("      ");
            }
            sb.append(column.getColumn());
            if (i != table.getColumns().size() -1) {
                sb.append(", ");
            }
        }
        sb.append(NEXT_LINE).append("      )");
        sb.append(NEXT_LINE).append("    values (");
        for (int i = 0; i < table.getColumns().size(); i++) {
            ColumnEntity column = table.getColumns().get(i);
            if (i % 3 == 0) {
                sb.append(NEXT_LINE).append("      ");
            }
            sb.append("#{").append(column.getProperty()).append(",jdbcType=").append(column.getJdbcType()).append("}");
            if (i != table.getColumns().size() -1) {
                sb.append(", ");
            }
        }
        sb.append(NEXT_LINE).append("      )");
        sb.append(NEXT_LINE).append("  </insert>");
        return sb.toString();
    }

}
