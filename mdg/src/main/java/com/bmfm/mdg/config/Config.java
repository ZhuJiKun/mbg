package com.bmfm.mdg.config;

import java.util.List;

/**
 * @author jikun.zhu
 * @date 2020/7/1 10:56 上午
 */
public class Config {

    private static Config config = new Config();

    /**
     * 是否转驼峰
     */
    private boolean formatHump = true;

    /**
     * 生成的实体名称
     */
    private String beanName;

    /**
     * sql语句
     */
    private List<String> sql;

    /**
     * 生成的实体包的路径
     */
    private String DOPackagePath;

    /**
     * 生成的dao包的路径
     */
    private String daoPackagePath;

    /**
     * 输出的文件路径
     */
    private String outPath;

    /**
     * PK
     */
    private String PK;

    /**
     * 作者
     */
    private String author;

    public static boolean isFormatHump() {
        return config.formatHump;
    }

    public static void setFormatHump(boolean formatHump) {
        config.formatHump = formatHump;
    }

    public static String getBeanName() {
        return config.beanName;
    }

    public static void setBeanName(String beanName) {
        config.beanName = beanName;
    }

    public static List<String> getSql() {
        return config.sql;
    }

    public static void setSql(List<String> sql) {
        config.sql = sql;
    }

    public static String getOutPath() {
        return config.outPath;
    }

    public static void setOutPath(String outPath) {
        config.outPath = outPath;
    }

    public static String getDOPackagePath() {
        return config.DOPackagePath;
    }

    public static void setDOPackagePath(String DOPackagePath) {
        config.DOPackagePath = DOPackagePath;
    }

    public static String getDaoPackagePath() {
        return config.daoPackagePath;
    }

    public static void setDaoPackagePath(String daoPackagePath) {
        config.daoPackagePath = daoPackagePath;
    }

    public static String getPK() {
        return config.PK;
    }

    public static void setPK(String PK) {
        config.PK = PK;
    }

    public static String getAuthor() {
        return config.author;
    }

    public static void setAuthor(String author) {
        config.author = author;
    }
}
