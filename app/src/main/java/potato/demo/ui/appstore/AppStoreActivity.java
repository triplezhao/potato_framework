package potato.demo.ui.appstore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.mozillaonline.providers.DownloadManager;
import com.mozillaonline.providers.downloads.DownloadService;
import com.mozillaonline.providers.downloads.ui.DownloadListActivity;
import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.base.BaseDefaultListActivity;
import potato.demo.chips.common.AppPool;
import potato.demo.data.bean.AppBean;
import potato.demo.data.bean.DataSource;

public class AppStoreActivity extends BaseDefaultListActivity {


    private BroadcastReceiver mReceiver;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.include_a) LinearLayout include_a;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_store);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        startDownloadService();
        mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                showDownloadList();
            }
        };
        registerReceiver(mReceiver, new IntentFilter(
                DownloadManager.ACTION_NOTIFICATION_CLICKED));

        initListView();
        reqRefresh();


    }

    private void startDownloadService() {
        Intent intent = new Intent();
        intent.setClass(this, DownloadService.class);
        startService(intent);
    }

    private void showDownloadList() {
        Intent intent = new Intent();
        intent.setClass(this, DownloadListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public PotatoBaseRecyclerViewAdapter getAdapter() {
        return new AppListAdapter(mContext);
    }

    @Override
    public void reqRefresh() {

        AppPool.FixedPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onRefreshSucc(null);
                    }
                });

            }
        });


    }


    @Override
    public void reqLoadMore() {
        AppPool.FixedPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onLoadMoreSucc(null);
                    }
                });
            }
        });
    }


    private ArrayList<AppBean> getRandomSublist(int amount) {

        ArrayList<AppBean> list = new ArrayList<AppBean>(amount);
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            list.add(new AppBean("title=" + DataSource.NAMES[i],
                    "content=" + DataSource.NAMES[random.nextInt(DataSource.NAMES.length)],
                    DataSource.IMAGES[random.nextInt(DataSource.NAMES.length)],
                    DataSource.URLS[random.nextInt(DataSource.URLS.length)]
            ));
        }
        return list;
    }


}
