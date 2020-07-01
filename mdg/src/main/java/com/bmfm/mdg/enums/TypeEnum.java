package com.bmfm.mdg.enums;

/**
 *
 * @author jikun.zhu
 * @date 2020/7/1 11:19 上午
 */
public enum TypeEnum {

    DATE_TIME("DATETIME", "DATE"),
    CHAR("CHAR", "String"),
    BIG_INT("BIGINT", "Long"),
    STRING("VARCHAR", "String");


    /**
     * 数据库字段类型
     */
    private String jdbcType;

    /**
     * java字段类型
     */
    private String javaType;


    public static TypeEnum getByJdbcType(String jdbcType){
        for (TypeEnum types : TypeEnum.values()) {
            if (types.jdbcType.equalsIgnoreCase(jdbcType)) {
                return types;
            }
        }
        throw new IllegalArgumentException("the jdbcType '" + jdbcType + "' not found.");
    }

    TypeEnum(String jdbcType, String javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
