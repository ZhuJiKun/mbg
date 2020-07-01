package com.bmfm.mdg.core;

import com.bmfm.mdg.config.Config;
import com.bmfm.mdg.entity.MyStringBuilder;
import com.bmfm.mdg.entity.TableEntity;
import com.bmfm.mdg.utils.DateUtil;

import java.util.Date;

/**
 * xx
 *
 * @author jikun.zhu
 * @date 2020/7/1 4:43 下午
 */
public interface Out {

    void output(TableEntity table) throws Exception;

    default String notes(String name) {
        MyStringBuilder sb = new MyStringBuilder(32);
        sb.append("/**");
        if (name != null && name.length() > 0) {
            sb.appendPre(" * ").append(name);
        }
        sb.appendPre(" *");
        if (Config.getAuthor() != null) {
            sb.appendPre(" * @author ").append(Config.getAuthor());
        }
        sb.appendPre(" * @date ").append(DateUtil.format(new Date()))
                .appendPre(" */").nextLine();
        return sb.toString();
    }

}
