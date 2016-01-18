package potato.demo.mvvm.v.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.nostra13.universalimageloader.core.ImageLoader;
import potato.demo.chips.base.BaseActivity;
import potato.demo.R;
import potato.demo.mvvm.m.bean.ABean;
import potato.demo.mvvm.v.adapter.BListAdapter;
import potato.demo.mvvm.v.fragment.Cheeses;
import potato.demo.databinding.ActivityBBinding;
import com.potato.library.view.refresh.PotatoRecyclerSwipeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BActivity extends BaseActivity {

    ActivityBBinding binding;
    BListAdapter mAdapter;
    List<ABean> mValues = new ArrayList<ABean>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show);
//        ImageView iv = (ImageView)findViewById(R.id.iv_icon);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_b);


        mAdapter = new BListAdapter(mContext);

        binding.swipeContainer.setRecyclerView(binding.list,mAdapter);
        binding.swipeContainer.setLayoutManager(new LinearLayoutManager(mContext));
        binding.swipeContainer.setFooterView(binding.list, R.layout.potato_listview_footer);

        binding.swipeContainer.setColorSchemeResources(R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mValues = getRandomSublist(Cheeses.sCheeseStrings, 30);
                        mAdapter.setDataList(mValues);
//                        mAdapter.notifyDataSetChanged();
                        binding.swipeContainer.setRefreshing(false);
                    }
                }, 300);
            }
        });
        binding.swipeContainer.setOnLoadListener(new PotatoRecyclerSwipeLayout.OnLoadListener(){
            @Override
            public void onLoad() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mValues.addAll(getRandomSublist(Cheeses.sCheeseStrings, 30));
                        int lastCount = mValues.size();
                        mAdapter.setDataList(mValues);
//                        mAdapter.notifyDataSetChanged();
                        mAdapter.notifyItemInserted(lastCount);
                        binding.swipeContainer.setLoading(false);
                    }
                }, 300);

            }
        });

        binding.swipeContainer.setScrollStateLisener(new PotatoRecyclerSwipeLayout.ScrollStateLisener() {
            @Override
            public void pause() {
                ImageLoader.getInstance().pause();

            }

            @Override
            public void resume() {
                ImageLoader.getInstance().resume();
            }
        });

        binding.swipeContainer.setRefreshing(true);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mValues = getRandomSublist(Cheeses.sCheeseStrings, 30);
                mAdapter.setDataList(mValues);
                binding.swipeContainer.setRefreshing(false);
            }
        }, 300);
    }

    private List<ABean> getRandomSublist(String[] array, int amount) {

        ArrayList<ABean> list = new ArrayList<ABean>(amount);
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            list.add(new ABean("title=" + i, Cheeses.getRandomIcon(), "content=" + array[random.nextInt(array.length)]));
        }
        return list;
    }

}
