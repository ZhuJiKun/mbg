package com.bmfm.mdg.utils;

/**
 * @author jikun.zhu
 * @date 2020/7/1 11:46 上午
 */
public class StringUtils {

    /**
     * 转驼峰
     */
    public static String toHump(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        boolean hump = false;
        int i = 0;
        for (char c : str.toCharArray()) {
            i++;
            if (c == '_') {
                hump = true;
                continue;
            }
            if (hump) {
                if (c >= 'a' && c <= 'z') {
                    c = (char) (c - 32);
                }
                hump = !hump;
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
