package com.cyou.demo.youku.ui.act;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cyou.demo.R;
import com.cyou.demo.databinding.ActivityYkCachingBinding;
import com.cyou.demo.databinding.ItemYkVideoDownloadingBinding;
import com.cyou.frame.base.BaseActivity;
import com.cyou.frame.base.BaseListAdapter;
import com.cyou.frame.base.BaseViewBinder;
import com.cyou.frame.base.BaseViewHolder;
import com.cyou.frame.util.ImageLoaderUtil;
import com.youku.service.download.DownloadInfo;
import com.youku.service.download.DownloadManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;


/**
 * 简单展示正在的视频，用户可定制自己的界面
 */

public class YKCachingActivity extends BaseActivity {

    //通过DownloadManager获取下载视频列表
    private DownloadManager downloadManager;

    //记录当前下载中的视频列表
    private ArrayList<DownloadInfo> downloadingList_show = new ArrayList<DownloadInfo>();

    //数据Adapter
    private BaseListAdapter adapter;
    private ActivityYkCachingBinding mBinding;
    private static final int MSG_STATE_CHANGE = 0;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            if (msg.what == MSG_STATE_CHANGE) {
                readData();
                //更改界面现实状态
                adapter.setDataList(downloadingList_show);
                adapter.notifyDataSetChanged();
            }
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_yk_caching);

        readData();

        adapter = new BaseListAdapter(this, new YKVideoViewBinder());
        adapter.setDataList(downloadingList_show);
        mBinding.list.setAdapter(adapter);

    }

    /**
     * 获取当前正在下载的视频信息
     */
    private void readData() {
        downloadingList_show.clear();

        //通过DownloadManager类的getDownloadingData()接口函数获取已经下载的视频信息
        downloadManager = DownloadManager.getInstance();
        Iterator iter = downloadManager.getDownloadingData().entrySet().iterator();

        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            //视频信息实体类用DownloadInfo表示
            DownloadInfo info = (DownloadInfo) entry.getValue();
            downloadingList_show.add(info);
        }

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        finish();
        super.onDestroy();
    }


    class ViewHolder extends BaseViewHolder {

        private ViewDataBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

    class YKVideoViewBinder extends BaseViewBinder<ViewHolder> {

        public YKVideoViewBinder() {
        }

        @Override
        public ViewHolder onCreateViewHolder(int position, int type, ViewGroup parent) {

            ItemYkVideoDownloadingBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_yk_video_downloading,
                    parent,
                    false);
            ViewHolder holder = new ViewHolder(binding.getRoot());
            holder.setBinding(binding);

            return holder;
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, Object object, int position, int type) {
            final ItemYkVideoDownloadingBinding binding = (ItemYkVideoDownloadingBinding) holder.getBinding();
            final DownloadInfo info = (DownloadInfo) object;

            TextView tv_title = binding.title;                    //视频标题
            final Button btn_pause_continue = binding.pause;       //暂停/继续 按钮
            Button btn_cancel = binding.cancel;            //须消当前下载任务按钮

            //展示视频标题
            tv_title.setText(info.title);

            if (info.state == DownloadInfo.STATE_DOWNLOADING) {                            //当前视频的下载状态：正在下载
                btn_pause_continue.setText("正在下载");
            } else if (info.state == DownloadInfo.STATE_PAUSE) {                                //当前视频的下载状态：暂停中
                btn_pause_continue.setText("暂停中");
            } else if (info.state == DownloadInfo.STATE_INIT                                //当前视频的下载状态：等待中
                    || info.state == DownloadInfo.STATE_EXCEPTION
                    || info.state == DownloadInfo.STATE_WAITING) {
                btn_pause_continue.setText("等待中");
            }


            ImageLoaderUtil.displayImage(info.imgUrl, binding.ivPic, R.drawable.def_gray_small);

            //单击暂停/继续按钮改变当前下载任务的状态
            btn_pause_continue.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (info.state == DownloadInfo.STATE_DOWNLOADING
                            || info.state == DownloadInfo.STATE_WAITING
                            || info.state == DownloadInfo.STATE_INIT
                            || info.state == DownloadInfo.STATE_EXCEPTION) {

                        //处于以上几种状态时单击进行暂停任务
                        downloadManager.pauseDownload(info.taskId);

                    } else if (info.state == DownloadInfo.STATE_PAUSE) {
                        //处于暂停状态时单击进行继续任务
                        downloadManager.startDownload(info.taskId);

                    }

                    mHandler.sendEmptyMessageDelayed(0, 200);
                }
            });

            //取消当前下载任务
            btn_cancel.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new AsyncTask<Void, Void, Boolean>() {

                        @Override
                        protected Boolean doInBackground(Void... params) {
                            // TODO Auto-generated method stub
                            //通过DonwloadManager类的deleteDownloading()接口函数删除所限泽的正在下载的任务
                            return downloadManager.deleteDownloading(info.taskId);
                        }

                    }.execute();
                    //通知更新
                    mHandler.sendEmptyMessageDelayed(0, 100);
                }


            });


        }

    }


}
