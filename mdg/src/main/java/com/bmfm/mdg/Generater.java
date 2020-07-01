package com.bmfm.mdg;

import com.bmfm.mdg.config.Config;
import com.bmfm.mdg.entity.TableEntity;
import com.bmfm.mdg.utils.ReadUtil;
import com.bmfm.mdg.core.TableService;
import com.bmfm.mdg.utils.WriteUtil;

/**
 * 生成器
 * @author jikun.zhu
 * @date 2020/7/1 10:54 上午
 */
public class Generater {

    public static void main(String[] args) throws Exception {
        Generater generater = new Generater();
        generater.generate();
    }


    public void generate() throws Exception {
        ReadUtil.readConfig();
        TableEntity table = TableService.sql2Entity(Config.getSql());
        WriteUtil.write(table);
    }
}
