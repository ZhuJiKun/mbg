package com.bmfm.mdg.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jikun.zhu
 * @date 2020/7/1 11:11 上午
 */
public class TableEntity implements Serializable {

    private static final long serialVersionUID = 5925097710270890680L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * format后的名称
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

    /**
     * 包含的字段
     */
    private List<ColumnEntity> columns;

    /**
     * 是否包含date类型的数据
     */
    private boolean hasDate;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnEntity> columns) {
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasDate() {
        return hasDate;
    }

    public void setHasDate(boolean hasDate) {
        this.hasDate = hasDate;
    }
}
