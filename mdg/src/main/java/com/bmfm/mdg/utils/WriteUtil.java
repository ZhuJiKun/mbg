package com.bmfm.mdg.utils;

import com.bmfm.mdg.entity.TableEntity;
import com.bmfm.mdg.core.OutWriter;

/**
 * @date 2020/7/1 3:53 下午
 */
public class WriteUtil {

    public static void write(TableEntity table) throws Exception {
        new OutWriter(table).out();
    }
}
