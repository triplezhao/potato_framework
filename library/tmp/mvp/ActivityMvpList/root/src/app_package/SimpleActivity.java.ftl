
package ${packageName};

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
<#if applicationPackage??>import ${applicationPackage}.R;</#if>
import javax.inject.Inject;

public class ${mvpClass}Activity extends BaseActivity implements ${mvpClass}.V {

    public static final String TAG = ${mvpClass}Activity.class.getSimpleName();
    
    @Bind(R.id.include_a) 
	LinearLayout include_a;
    @Bind(R.id.toolbar) 
	Toolbar toolbar;
	
	@Bind(R.id.swipe_container)
    PotatoRecyclerSwipeLayout mSwipeContainer;
    @Bind(R.id.list)
    RecyclerView listview;
    @Bind(R.id.empty_view)
    NormalEmptyView mNormalEmptyView;

    int mTotal = 0;
    int mPage = 0;
    ArrayList mList = new ArrayList();
	PotatoBaseRecyclerViewAdapter mAdapter;
	${mvpClass}ListEntity mEntity;
	
    @Inject ${mvpClass}Presenter presenter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.${layoutActivityName});
        ButterKnife.bind(this);

        Dagger${mvpClass}_C.builder().module(new ${mvpClass}.Module(this)).build().inject(this);
        mAdapter = new ${mvpClass}ListAdapter(mContext);
        mEntity = new ${mvpClass}ListEntity();

        initListView();

        mSwipeContainer.showProgress();
        presenter.reqRefresh();
        
    }


    public void initListView() {

        mSwipeContainer.setRecyclerView(listview, mAdapter);
        mSwipeContainer.setLayoutManager(new LinearLayoutManager(mContext));
       /*
       瀑布流
       * //setLayoutManager
        ExStaggeredGridLayoutManager manager = new ExStaggeredGridLayoutManager (2, StaggeredGridLayoutManager.VERTICAL);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) mRecyclerView.getAdapter(), manager.getSpanCount()));
        mSwipeContainer.setLayoutManager(manager);

       网格
        //setLayoutManager
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) mRecyclerView.getAdapter(), manager.getSpanCount()));
        mRecyclerView.setLayoutManager(manager);
       * */
        mSwipeContainer.setFooterView(listview, com.potato.library.R.layout.potato_listview_footer);

        mSwipeContainer.setScrollStateLisener(new PotatoRecyclerSwipeLayout.ScrollStateLisener() {
            @Override
            public void pause() {
                ImageLoader.getInstance().pause();
            }

            @Override
            public void resume() {
                ImageLoader.getInstance().resume();
            }
        });
        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.reqRefresh();
            }
        });
        mSwipeContainer.setOnLoadListener(new PotatoRecyclerSwipeLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                presenter.reqLoadMore(mEntity.maxPublicDate);
            }
        });

        mSwipeContainer.setEmptyView(mNormalEmptyView);
        mNormalEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwipeContainer.showProgress();
                presenter.reqRefresh();
            }
        });

        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        manager.setSpanSizeLookup(new HFGridlayoutSpanSizeLookup(mSwipeContainer.getHFAdapter(), manager.getSpanCount()) {
            @Override
            public int getSpanSize(int position) {
                boolean isHeaderOrFooter = adapter.isHeader(position) || adapter.isFooter(position);
                if (isHeaderOrFooter) {
                    return mSpanSize;
                } else if (position % 3 == 0) {
                    return mSpanSize;
                } else {
                    return 1;
                }
            }
        });
        mSwipeContainer.setLayoutManager(manager);

    }

	
	
	@Override
    public void onRefreshSucc(${mvpClass}ListEntity entity) {
        mEntity = entity;
        mSwipeContainer.showSucc();
        mList = entity.list;
        mAdapter.setDataList(mList);
        mAdapter.notifyDataSetChanged();
        mSwipeContainer.setRefreshing(false);
        if (mList != null && mList.size() != 0) {
            mSwipeContainer.setLoadEnable(true);
        }

    }

    @Override
    public void onRefreshFail(String err) {
        mSwipeContainer.setLoadEnable(false);
        mSwipeContainer.setRefreshing(false);
    }
	
   @Override
    public void onLoadMoreSucc(${mvpClass}ListEntity entity) {
        mEntity = entity;
        mSwipeContainer.setLoading(false);
        ArrayList<${mvpClass}Bean> moreData = entity.list;
        if (moreData == null || moreData.size() == 0) {
            mSwipeContainer.setLoadEnable(false);
            return;
        }
        mList.addAll(moreData);
        mAdapter.setDataList(mList);
        mAdapter.notifyItemInserted(mList.size() - 1);
    }

    @Override
    public void onLoadMoreFail(String err) {
        mSwipeContainer.setLoadEnable(false);
        mSwipeContainer.setRefreshing(false);
    }
	
	@Override
    public void onClick(View v) {

		
	
    }
	
	public static class ${mvpClass}Adapter extends PotatoBaseRecyclerViewAdapter<${mvpClass}Adapter.VH> {

        public ${mvpClass}Adapter(Context context) {
            super(context);
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(
                    R.layout.item_${mvpClass},
                    parent,
                    false);
            VH holder = new VH(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(VH vh, int position) {
            final ${mvpClass} bean = (${mvpClass}) mData.get(position);

            vh.tv_title.setText(bean.getTitle());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    PageCtrl.start${mvpClass}DetailActivity(context, bean);
                }
            });
            ImageLoaderUtil.displayImage(bean.getBigCover(), vh.iv_pic, R.drawable.def_gray_big);

        }


        static class VH extends RecyclerView.ViewHolder {

            @Bind(R.id.iv_pic)
            ImageView iv_pic;
            @Bind(R.id.tv_title)
            TextView tv_title;

            public VH(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
	
	
}
