package com.bmfm.mdg.utils;

import com.bmfm.mdg.config.Config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jikun.zhu
 * @date 2020/7/1 2:38 下午
 */
public class ReadUtil {

    private final static String tag = "分割线";

    public static void readConfig() throws Exception {

        InputStream stream = Thread.currentThread().getClass().getResourceAsStream("/mbg.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;

        List<String> configs = new ArrayList<>();
        List<String> sqls = new ArrayList<>();

        boolean flag = false;

        while ((line = reader.readLine()) != null) {
            if (flag) {
                sqls.add(line);
                continue;
            }

            if (tag.equals(line)) {
                flag = true;
                continue;
            }

            configs.add(line);
        }


        Config.setSql(sqls);

        // 解析配置
        for (String s : configs) {
            String[] ss = s.split("=");
            switch (ss[0]) {
                case "formatHump":
                    Config.setFormatHump(Boolean.parseBoolean(ss[1]));
                    break;
                case "beanName":
                    Config.setBeanName(ss[1]);
                    break;
                case "DOPackagePath":
                    Config.setDOPackagePath(ss[1]);
                case "daoPackagePath":
                    Config.setDaoPackagePath(ss[1]);
                case "outPath":
                    Config.setOutPath(ss[1]);
                default:
                    break;
            }
        }
    }


}
