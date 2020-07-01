package com.bmfm.mdg.entity;

import java.io.Serializable;

/**
 * @author jikun.zhu
 * @date 2020/7/1 11:04 上午
 */
public class ColumnEntity implements Serializable {
    private static final long serialVersionUID = 659260184919167450L;

    /**
     * 数据库字段
     */
    private String column;

    /**
     * 数据库字段类型
     */
    private String jdbcType;

    /**
     * java字段
     */
    private String property;

    /**
     * java字段类型
     */
    private String javaType;

    /**
     * 描述
     */
    private String desc;

    /**
     * 是否是主键
     */
    private boolean id;


    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }
}
