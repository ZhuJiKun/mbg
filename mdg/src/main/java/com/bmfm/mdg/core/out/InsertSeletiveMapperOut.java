package com.bmfm.mdg.core.out;

import com.bmfm.mdg.config.Config;
import com.bmfm.mdg.core.MapperOut;
import com.bmfm.mdg.entity.ColumnEntity;
import com.bmfm.mdg.entity.MyStringBuilder;
import com.bmfm.mdg.entity.TableEntity;

/**
 * @author jikun.zhu
 * @date 2020/7/1 5:00 下午
 */
public class InsertSeletiveMapperOut extends MapperOut {

    @Override
    public String outDao(TableEntity table) {
        return "    int insertSelective(" + table.getName() + " record);";
    }

    @Override
    public String outMapper(TableEntity table) {
        MyStringBuilder sb = new MyStringBuilder(128);
        sb.append("  <insert id=\"insertSelective\" parameterType=\"").append(Config.getDOPackagePath()).append(".").append(table.getName()).appendNext("\">");
        sb.append("    insert into ").appendNext(table.getTableName());

        sb.append("    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        for (int i = 0; i < table.getColumns().size(); i++) {
            ColumnEntity column = table.getColumns().get(i);

            sb.appendPre("      <if test=\"").append(column.getProperty()).append(" != null\">");
            sb.appendPre("        `").append(column.getColumn()).append("`,");
            sb.appendPre("      </if>");
        }
        sb.appendPre("    </trim>");

        sb.appendPre("    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">");
        for (int i = 0; i < table.getColumns().size(); i++) {
            ColumnEntity column = table.getColumns().get(i);

            sb.appendPre("      <if test=\"").append(column.getProperty()).append(" != null\">");
            sb.appendPre("        #{").append(column.getProperty()).append(",jdbcType=").append(column.getJdbcType()).append("},");
            sb.appendPre("      </if>");
        }
        sb.appendPre("    </trim>");
        sb.appendPre("  </insert>");

        return sb.toString();
    }

}
