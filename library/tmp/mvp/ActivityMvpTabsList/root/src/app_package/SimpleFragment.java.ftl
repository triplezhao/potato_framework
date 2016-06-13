package ${packageName};

<#if applicationPackage??>import ${applicationPackage}.R;</#if>
import javax.inject.Inject;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ${fragmentMvpClass}Fragment extends BaseFragment implements ${fragmentMvpClass}.V {

    public static final String TAG = ${fragmentMvpClass}Fragment.class.getSimpleName();
    
	public static final String EXTRARS_SECTION_ID = "EXTRARS_SECTION_ID";
    public static final String EXTRARS_TITLE = "EXTRARS_TITLE";
    public long mSectionId;
    public String mTitle;
    public int mTotal = 0;
    public int mPage = 0;
    public ArrayList<Object> mList = new ArrayList<Object>();
    public PotatoBaseRecyclerViewAdapter mAdapter;
    public Object mEntity;

    @Bind(R.id.swipe_container)
    PotatoRecyclerSwipeLayout mSwipeContainer;
    @Bind(R.id.list)
    RecyclerView listview;
    @Bind(R.id.empty_view)
    NormalEmptyView mNormalEmptyView;
    @Bind(R.id.include_a)
    LinearLayout include_a;
    @Inject ${fragmentMvpClass}Presenter presenter;


	
	 public static ${fragmentClass} instance(Context context,long sectionId, String title){
        Bundle args = new Bundle();
        args.putLong(${fragmentClass}.EXTRARS_SECTION_ID, sectionId);
        args.putString(${fragmentClass}.EXTRARS_TITLE, title);
        ${fragmentClass} pageFragement = (${fragmentClass}) Fragment.instantiate(context, xxxListFragment.class.getName(), args);
		return pageFragement;
    }
	
	
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.${layoutFragmentName}, container, false);

        ButterKnife.bind(this, view);
                       
        Dagger${fragmentMvpClass}_C.builder().module(new ${fragmentMvpClass}.Module(this)).build().inject(this);
       
        mSectionId = getArguments() == null ? 0 : getArguments().getLong(EXTRARS_SECTION_ID);
        mTitle = getArguments() == null ? "" : getArguments().getString(EXTRARS_TITLE);
      
        mAdapter = new ${fragmentMvpClass}Adapter(mContext);
        mEntity = new Object();

        initListView();

        mSwipeContainer.showProgress();
        presenter.reqRefresh();
     	
		return view;
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
    public void onRefreshSucc(Object entity) {
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
    public void onLoadMoreSucc(Object entity) {
        mEntity = entity;
        mSwipeContainer.setLoading(false);
        ArrayList<Object> moreData = entity.list;
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
    public void onCacheLoaded(Object entity) {

    }

    @Override
    public long getSectionId() {
        return mSectionId;
    }


    @Override
    public void onClick(View v) {

    }

    public static class ${fragmentMvpClass}Adapter extends PotatoBaseRecyclerViewAdapter<${fragmentMvpClass}Adapter.VH> {

        public ${fragmentMvpClass}Adapter(Context context) {
            super(context);
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(
                    R.layout.item_jiongtu_list,
                    parent,
                    false);
            VH holder = new VH(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(VH vh, int position) {
            final Object bean = (Object) mData.get(position);

            vh.tv_title.setText(bean.getTitle());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    PageCtrl.startJiongTuDetailActivity(context, bean);
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