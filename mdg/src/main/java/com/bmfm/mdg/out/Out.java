package com.bmfm.mdg.out;

import com.bmfm.mdg.entity.TableEntity;

/**
 * @author jikun.zhu
 * @date 2020/7/1 4:43 下午
 */
public interface Out {

    public final static String NEXT_LINE = "\r\n";

    void output(TableEntity table) throws Exception;

}
