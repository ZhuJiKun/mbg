package com.bmfm.mdg.core;

import com.bmfm.mdg.config.Config;
import com.bmfm.mdg.entity.ColumnEntity;
import com.bmfm.mdg.entity.TableEntity;
import com.bmfm.mdg.enums.TypeEnum;
import com.bmfm.mdg.utils.StringUtils;

import java.util.*;

/**
 * @author jikun.zhu
 * @date 2020/7/1 11:16 上午
 */
public class TableService {

    private static final String SPACE = " ";
    private static final String EMPTY = "";
    private static final String LF = "\n";
    private static final String CR = "\r";

    // 保留关键词
    private static final Set<String> KEYS = new HashSet<>();

    static {
        String str = "CREATE TABLE IF NOT EXISTS KEY UNIQUE PRIMARY";
        KEYS.addAll(Arrays.asList(str.split(SPACE)));
    }


    // 类型关键词
    private static final Set<String> TYPE_KEYS = new HashSet<>();
    static {
        for (TypeEnum type : TypeEnum.values()) {
            TYPE_KEYS.add(type.getJdbcType());
        }
    }


    public static TableEntity sql2Entity(List<String> createSql) {
        TableEntity table = new TableEntity();

        // 设置表的信息
        table.setTableName(findTableName(toArr(createSql.get(0))));
        table.setName(beanName(table.getTableName()));
        table.setDesc(findTableDesc(toArr(createSql.get(createSql.size() - 1))));


        // 设置字段信息
        List<ColumnEntity> columns = new ArrayList<>();
        for (int i = 1; i < createSql.size() - 1; i++) {
            String sql = createSql.get(i);
            String[] arr = toArr(sql);

            if (KEYS.contains(arr[0].toUpperCase())) {
                continue;
            }

            ColumnEntity entity = new ColumnEntity();
            entity.setColumn(format(arr[0]));

            TypeEnum type = findType(arr[1]);
            if(type == TypeEnum.DATE_TIME) {
                table.setHasDate(true);
            }
            entity.setJdbcType(type.getJdbcType());
            entity.setJavaType(type.getJavaType());
            entity.setProperty(Config.isFormatHump() ? StringUtils.toHump(entity.getColumn()) : entity.getColumn());

            // desc设置
            for (int j = arr.length - 1; j > 0; j--) {
                if ("COMMENT".equalsIgnoreCase(arr[j])) {
                    String desc = arr[j+1];
                    desc = format(desc);
                    entity.setDesc(desc.replace(",", EMPTY));
                    break;
                }
            }
            columns.add(entity);
        }
        table.setColumns(columns);

        return table;
    }


    /**
     * 按空格分离
     */
    private static String[] toArr(String createSql) {
        String[] arr = createSql.split(SPACE);
        List<String> list = new ArrayList<>();
        for (String s : arr) {
            if (s.length() > 0)
                list.add(s);
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 设置表名
     */
    private static String findTableName(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];

            if (KEYS.contains(s.toUpperCase())) {
                continue;
            }

            // tableName后面可能有(
            s = format(s);
            if (s.indexOf(s.length() - 1) == '(') {
                s = s.substring(0, s.length() - 1);
            }
            return s;
        }
        throw new IllegalArgumentException("the table name not found.");
    }

    /**
     * 设置表描述
     */
    private static String findTableDesc(String[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            if ("COMMENT".equalsIgnoreCase(arr[i])) {
                String desc = arr[i+1];
                desc = format(desc);
                return desc.replace(",", EMPTY);
            }
            if (arr[i].startsWith("COMMENT") || arr[i].startsWith("comment")) {
                StringBuilder sb = new StringBuilder();
                for (int j = i; j < arr.length; j++) {
                    sb.append(arr[j]);
                }
                StringBuilder desc = new StringBuilder();
                boolean t = false;
                for (char c : sb.toString().toCharArray()){
                    if (t) {
                        if (c == '\'') {
                            return desc.toString();
                        }
                        desc.append(c);
                    }

                    if (c == '\'') {
                        t = true;
                    }
                }

            }
        }
        return null;
    }

    /**
     * 设置实体名称
     */
    private static String beanName(String tableName) {
        if (Config.getBeanName() != null) {
            return Config.getBeanName();
        }
        if (!Config.isFormatHump()) {
            return tableName;
        }
        String name = StringUtils.toHump(tableName);
        char c = name.charAt(0);
        if (c >= 'a' && c <= 'z') {
            c = (char) (c - 32);
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.setCharAt(0, c);
            return sb.toString();
        }
        return name;
    }


    /**
     * 格式化
     */
    private static String format(String str) {
        str = str.replaceAll("'", EMPTY);
        str = str.replaceAll("`", EMPTY);
        return str;
    }


    /**
     * 查询类型
     */
    private static TypeEnum findType(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (char c : str.toCharArray()) {
            if (c == '(') {
                break;
            }
            sb.append(c);
        }
        return TypeEnum.getByJdbcType(sb.toString());
    }
}
