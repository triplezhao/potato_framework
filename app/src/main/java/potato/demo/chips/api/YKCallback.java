package potato.demo.chips.api;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * 修订历史：
 * ================================================
 */
public abstract class YKCallback<T> extends AbsCallback<T> {

    private BaseResultEntity entity;

    public BaseResultEntity getEntity() {
        return entity;
    }

    public void setEntity(BaseResultEntity entity) {
        this.entity = entity;
    }

    //该方法是子线程处理，不能做ui相关的工作
    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        String responseData = response.body().string();
        if (TextUtils.isEmpty(responseData)) return null;

        /**
         * 一般来说，服务器返回的响应码都包含 code，msg，data 三部分，在此根据自己的业务需要完成相应的逻辑判断
         * 以下只是一个示例，具体业务具体实现
         */
        if (entity == null) {
            return null;
        } else {
            entity = entity.parse(responseData);
        }

        if (entity.isSucc()) {
            return (T) entity;
        } else {
            //如果要更新UI，需要使用handler，可以如下方式实现，也可以自己写handler
            OkHttpUtils.getInstance().getDelivery().post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(OkHttpUtils.getContext(), "错误代码：" + entity.code + "，错误信息：" + entity.message, Toast.LENGTH_SHORT).show();
                }
            });
            throw new Exception("错误代码：" + entity.code + "，错误信息：" + entity.message);
        }
    }

    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    public abstract void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e);


    /**
     * @author ztw 这个类只提供基础的解析方法，每个接口对应的解析方法在.parse.api包下面。
     */
    public static abstract class YKResultEntity extends BaseResultEntity {
        @Override
        public boolean isSucc() {
            return code == 0;
        }
        @Override
        public String getMsg() {
            return message;
        }
    }

}
