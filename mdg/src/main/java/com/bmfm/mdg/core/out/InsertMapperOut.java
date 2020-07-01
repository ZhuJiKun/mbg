package com.bmfm.mdg.core.out;

import com.bmfm.mdg.config.Config;
import com.bmfm.mdg.entity.ColumnEntity;
import com.bmfm.mdg.entity.MyStringBuilder;
import com.bmfm.mdg.entity.TableEntity;
import com.bmfm.mdg.core.MapperOut;

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
        MyStringBuilder sb = new MyStringBuilder(128);
        sb.append("  <insert id=\"insert\" parameterType=\"").append(Config.getDOPackagePath()).append(".").append(table.getName()).appendNext("\">");
        sb.append("    insert into ").append(table.getTableName()).append(" (");
        for (int i = 0; i < table.getColumns().size(); i++) {
            ColumnEntity column = table.getColumns().get(i);
            if (i % 3 == 0) {
                sb.appendPre("      ");
            }
            sb.append(column.getColumn());
            if (i != table.getColumns().size() -1) {
                sb.append(", ");
            }
        }
        sb.appendPre("      )");
        sb.appendPre("    values (");
        for (int i = 0; i < table.getColumns().size(); i++) {
            ColumnEntity column = table.getColumns().get(i);
            if (i % 3 == 0) {
                sb.appendPre("      ");
            }
            sb.append("#{").append(column.getProperty()).append(",jdbcType=").append(column.getJdbcType()).append("}");
            if (i != table.getColumns().size() -1) {
                sb.append(", ");
            }
        }
        sb.appendPre("      )");
        sb.appendPre("  </insert>");
        return sb.toString();
    }

}
