package com.bmfm.mdg.core;

import com.bmfm.mdg.core.Out;
import com.bmfm.mdg.entity.TableEntity;

/**
 * @author jikun.zhu
 * @date 2020/7/1 4:47 下午
 */
public abstract class MapperOut implements Out {

    public abstract String outDao(TableEntity table);
    public abstract String outMapper(TableEntity table);

    public void output(TableEntity table) throws Exception{
        outDao(table);
        outMapper(table);
    }


}
