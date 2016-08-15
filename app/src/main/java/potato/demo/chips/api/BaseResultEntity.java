package potato.demo.chips.api;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ztw on 2016/5/25.
 */
public abstract class BaseResultEntity implements Serializable {
    /**
     * 返回码
     */
    public int code = -1;
    /**
     * 返回Message
     */
    public String message;

    /**
     * 返回总个数
     */
    public int total;
    /**
     * 列表数据
     */
    public ArrayList list = new ArrayList();


    public abstract boolean isSucc();

    public abstract String getMsg();

    public abstract BaseResultEntity parse(String json);
}
