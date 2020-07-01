package com.bmfm.mdg.entity;

/**
 * @author jikun.zhu
 * @Title:
 * @Description:
 * @date 2020/7/1 6:25 下午
 */
public class MyStringBuilder {
    private final static String NEXT_LINE = "\r\n";

    private StringBuilder sb;

    public MyStringBuilder(int length) {
        sb = new StringBuilder(length);
    }

    public MyStringBuilder append(String s) {
        sb.append(s);
        return this;
    }

    public MyStringBuilder appendNext(String s) {
        sb.append(s).append(NEXT_LINE);
        return this;
    }

    public MyStringBuilder appendPre(String s) {
        sb.append(NEXT_LINE).append(s);
        return this;
    }

    public MyStringBuilder nextLine() {
        sb.append(NEXT_LINE);
        return this;
    }

    public MyStringBuilder nextLine(int num) {
        for (int i=0; i<num; i++) {
            sb.append(NEXT_LINE);
        }
        return this;
    }

    public String toString() {
        return sb.toString();
    }

}
