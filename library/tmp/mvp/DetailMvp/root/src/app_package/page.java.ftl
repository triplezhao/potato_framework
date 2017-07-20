
package ${packageName};

<#if applicationPackage??>import ${applicationPackage}.R;</#if>
import javax.inject.Inject;
<#if isFragment>
    <#assign baseName="BaseFragment">
        <#else>
            <#assign baseName="BaseActivity">
</#if>

public class ${pageName} extends ${baseName} implements ${mvpClass}.V {

    public static final String TAG = ${pageName}.class.getSimpleName();
    public static final String EXTRA_ID = "EXTRA_ID";
    public String mId;
    @Inject ${presenterName} presenter;
    @Bind(R.id.empty_view) NormalEmptyView empty_view;
    ${mvpClass}Bean m${mvpClass}Bean;


<#if isFragment>
    public static ${pageName} instance(Context context, String cusid) {
        Bundle args = new Bundle();
        args.putString(EXTRA_ID, cusid);
        ${pageName} pageFragement = (${pageName}) Fragment.instantiate(context, ${pageName}.class.getName(), args);
        return pageFragement;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.${layoutName}, container, false);

        ButterKnife.bind(this, view);

        Dagger${mvpClass}_C.builder().module(new ${mvpClass}.Module(this)).build().inject(this);
        mId = getArguments() == null ? "" : getArguments().getString(EXTRA_ID);
        reqDetail();

        return view;
    }
        <#else>
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.${layoutName});
        ButterKnife.bind(this);
        Dagger${mvpClass}_C.builder().module(new ${mvpClass}.Module(this)).build().inject(this);
        mId = getIntent() == null ? "" : getIntent().getStringExtra(EXTRA_ID);
        reqDetail();
    }
</#if>
      @Override
       public void render(${mvpClass}Bean bean) {
                tvRealname.setText(bean.getClass());
                tvMobile.setText(bean.getClass());
                tvBingliCode.setText(bean.getClass());
                tvZxs.setText(bean.getClass());
                tvKeshi.setText(bean.getClass());
                tvProject.setText(bean.getClass());
                tvFenzhenClerk.setText(bean.getClass());
                //2是需要专车
                if (!TextUtils.isEmpty(bean.getIscar()) && bean
                .getIscar()
                .equals("2")) {
                tvJiuzhenStatus.setText("是");
                } else {
                tvJiuzhenStatus.setText("否");
                }
       }

       @Override
       public void reqDetail() {
            empty_view.setEmptyType(NormalEmptyView.EMPTY_TYPE_LOADING);
           presenter.reqDetail(mId);
       }

       @Override
       public void onReqSucc(${mvpClass}Entity entity) {
            m${mvpClass}Bean = entity.bean;
            empty_view.setEmptyType(NormalEmptyView.EMPTY_TYPE_GONE);
           render((${mvpClass}Bean) entity.bean);
       }

       @Override
       public void onReqFail(String msg) {
            empty_view.setEmptyType(NormalEmptyView.EMPTY_TYPE_ERROR);
       }
    <#if !isFragment>
             @OnClick(R.id.iv_back)
             public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.iv_back:
                        finish();
                    break;
                     }
             }
    </#if>
}
