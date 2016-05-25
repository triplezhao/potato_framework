package potato.demo.chips.api;


/**
 * @author ztw 这个类只提供基础的解析方法，每个接口对应的解析方法在.parse.api包下面。
 */
public abstract class JiongtuResultEntity extends BaseResultEntity {

    @Override
    public boolean isSucc() {
        return code == 0;
    }

    @Override
    public String getMsg() {
        return message;
    }
}
