package potato.demo.service;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;
import com.potato.library.update.bean.FirVersionInfo;
import com.potato.library.update.service.BaseUpdateService;
import com.potato.library.view.dialog.DialogUtil;

import org.json.JSONException;

import java.io.File;

import okhttp3.Request;
import okhttp3.Response;
import potato.demo.R;
import potato.demo.data.request.QIApi;

/**
 * Created by admin on 2016/8/22.
 */
public class UpdateService extends BaseUpdateService {


    public void setupFir(final Intent intent) {

        QIApi.checkVersion(CacheMode.NET_ONLY, "57bfe2d2959d697acf000e43", "ec98c72cd81b87a9d62d095a3b1f10f6", new StringCallback() {
            @Override
            public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                if(TextUtils.isEmpty(s)){
                    stopSelf();
                }else {
                    try {
                        isNewVersion(s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        stopSelf();
                    }
                }

            }
        });
    }

    @Override
    protected Dialog setInstallTipsDialog(FirVersionInfo info, final String installApkPath) {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_update, null,
                false);
        TextView tv_update = (TextView) view.findViewById(R.id.tv_update);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);

        tv_update.setText("立即安装");
        tv_content.setText("发现新版本已为您下载完毕，是否安装?");
        /*PackageInfo current = PackageUtils.getPackageInfo(this);
        tv_version.setText("当前版本"+current.versionName);*/
        final Dialog dialog = DialogUtil.createDialog(this, view);

        tv_update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                UIUtils.toast(UpdateService.this,"退出登录");
                onInstallClick(dialog, null, new File(installApkPath));
            }
        });
        return dialog;
    }

    @Override protected Dialog setDialogContent(final FirVersionInfo info) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_update, null,
                false);
        TextView tv_update = (TextView) view.findViewById(R.id.tv_update);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);

        tv_content.setText("发现新版本是否立即更新?");
        tv_update.setText("立即更新");
        /*PackageInfo current = PackageUtils.getPackageInfo(this);
        tv_version.setText("当前版本"+current.versionName);*/
        final Dialog dialog = DialogUtil.createDialog(this, view);

        tv_update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                UIUtils.toast(UpdateService.this,"退出登录");
                onDownloadClick(dialog, null, info);
            }
        });
        return dialog;
    }
    @Override protected Dialog alertDialog(String info) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_last_version, null,
                false);
        TextView tv_close = (TextView) view.findViewById(R.id.tv_close);

        /*PackageInfo current = PackageUtils.getPackageInfo(this);
        tv_version.setText("当前版本"+current.versionName);*/
        final Dialog dialog = DialogUtil.createDialog(this, view);

        tv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                UIUtils.toast(UpdateService.this,"退出登录");
                dialog.dismiss();
            }
        });
        return dialog;
    }
}
