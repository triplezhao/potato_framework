package com.potato.library.update.service;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.potato.library.R;
import com.potato.library.update.bean.FirVersionInfo;
import com.potato.library.update.utils.CommonUtils;

import java.io.File;

/**
 * Create on: 2016-07-31
 * Author: wangh
 * Summary: TODO
 */
public class DefaultDownloadService extends BaseUpdateService {
  @Override
  protected Dialog setInstallTipsDialog(FirVersionInfo info, final String installApkPath) {
    final AlertDialog.Builder builder =
        new AlertDialog.Builder(this, R.style.AlertDialog_AppCompat_Dialog);
    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialogInterface, int i) {
        onCancelInstallClick(null, dialogInterface);
      }
    }).setPositiveButton("安装", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialogInterface, int i) {
        onInstallClick(null, dialogInterface, new File(installApkPath));
      }
    }).setTitle(info.appName).setIcon(getApplicationInfo().icon).setMessage("已为您准备好新的版本,立刻安装?");

    return builder.create();
  }

  @Override protected Dialog setDialogContent(final FirVersionInfo info) {
    final AlertDialog.Builder builder =
        new AlertDialog.Builder(this, R.style.AlertDialog_AppCompat_Dialog);
    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialogInterface, int i) {
        onCancelDownloadClick(null, dialogInterface);
      }
    })
        .setPositiveButton("更新", new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialogInterface, int i) {
            onDownloadClick(null, dialogInterface, info);
          }
        })
        .setTitle(info.appName + "发现新版本")
        .setIcon(getApplicationInfo().icon)
        .setMessage("更新日期:"
            + info.updateDate
            + "\n"
            + "更新日志:\n"
            + info.changeLog
            + "\n"
            + "版本: v"
            + info.versionName
            + "."
            + info.versionCode
            + "\n安装包大小:"
            + CommonUtils.bytes2kb(info.fileSize));
    return builder.create();
  }

  @Override
  protected Dialog alertDialog(String info) {
    final android.app.AlertDialog.Builder builder =
            new android.app.AlertDialog.Builder(this, R.style.AlertDialog_AppCompat_Dialog);
    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialogInterface, int i) {

      }
    })
            .setMessage(info);
    return builder.create();/*todo:必须返回dialog对象*/
  }
}
