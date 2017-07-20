
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
    public static final String EXTRA_IS_EDIT = "EXTRA_IS_EDIT";
    public static final String EXTRA_OBJ     = "EXTRA_OBJ";
    public String mId;
    @Inject ${presenterName} presenter;
    @Bind(R.id.empty_view)           NormalEmptyView      empty_view;
    @Bind(R.id.tv_title)             TextView             tvTitle;
    @Bind(R.id.et_channelname)       ClearEditText        etChannelname;
    @Bind(R.id.et_clerkname)         ClearEditText        etClerkname;
    @Bind(R.id.et_mobile)            ClearEditText        etMobile;
    @Bind(R.id.tv_diqu)              TextView             tvDiqu;
    @Bind(R.id.ll_diqu)              LinearLayout         llDiqu;
    @Bind(R.id.et_address)           ClearEditText        etAddress;
    @Bind(R.id.rb_customer_source_0) RadioButton          rbCustomerSource0;
    @Bind(R.id.rb_customer_source_1) RadioButton          rbCustomerSource1;
    @Bind(R.id.tv_dev_name)          TextView             tvDevName;
    @Bind(R.id.ll_dev)               LinearLayout         llDev;

    public ${mvpClass}ParamBean paramBean = new ${mvpClass}ParamBean();
    public ${mvpClass}Bean m${mvpClass}Bean; //请求带过来的店家信息
    public boolean           isEdit; //请求带过来的店家信息


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
        isEdit = getIntent() == null ? false : getIntent().getBooleanExtra(EXTRA_IS_EDIT, false);
        if (isEdit) {
             tvTitle.setText("店家编辑");
        } else {
        //如果是新建，则开发人员默认选中自己
    //            paramBean.channel_kf_id = loginUser.getId();
    //            paramBean.channel_kf_name = loginUser.getClerkname();
    //            tvDevName.setText(loginUser.getClerkname());
        }
        Object object = getIntent() == null ? null : getIntent().getSerializableExtra(EXTRA_OBJ);
        if (object != null) {
        bean = (${mvpClass}Bean) object;
        paramBean = ${mvpClass}ParamBean.copyFrom(bean);
        renderOld(bean);
    }

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
        isEdit = getIntent() == null ? false : getIntent().getBooleanExtra(EXTRA_IS_EDIT, false);
        if (isEdit) {
        tvTitle.setText("店家编辑");
        } else {
        //如果是新建，则开发人员默认选中自己
        //            paramBean.channel_kf_id = loginUser.getId();
        //            paramBean.channel_kf_name = loginUser.getClerkname();
        //            tvDevName.setText(loginUser.getClerkname());
        }
        Object object = getIntent() == null ? null : getIntent().getSerializableExtra(EXTRA_OBJ);
        if (object != null) {
        bean = (${mvpClass}Bean) object;
        paramBean = ${mvpClass}ParamBean.copyFrom(bean);
        renderOld(bean);
       }
    }
</#if>

        @Override
        public void renderOld(FenzhenDetailBean bean) {
            etChannelname.setText(bean.channelname);
            etClerkname.setText(bean.clerkname);
            etMobile.setText(bean.mobile);
            tvDiqu.setText(bean.province + bean.city + bean.district);
            etAddress.setText(bean.address);

            //自主研发
            if (bean.customer_source.equals("0")) {
            rbCustomerSource0.setChecked(true);
            rbCustomerSource1.setChecked(false);

            } else {
            //转介绍
            rbCustomerSource1.setChecked(true);
            rbCustomerSource0.setChecked(false);
            }

            //如果是编辑模式，则手机号不能编辑，直接用初始化那个手机号
            etChannelname.setIconClear(R.drawable.trans_bg);
            etChannelname.setEnabled(false);
            etChannelname.setTextColor(getResources().getColor(R.color.ifsee_gray_txt_333));
            etChannelname.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

            //开发人员
            tvDevName.setText(bean.channel_kf_name);
        }

        @Override
        public boolean checkUIParams() {
                if (TextUtils.isEmpty(etChannelname
                .getText()
                .toString())) {
                UIUtils.toast(mContext, "请填写店家姓名");
                return false;
                } /*else if (TextUtils.isEmpty(etLogname.getText()
                .toString())) {
                UIUtils.toast(mContext, "请填写登录账号");
                return false;
                } */ else if (TextUtils.isEmpty(etClerkname
                .getText()
                .toString())) {
                UIUtils.toast(mContext, "请填写老板姓名");
                return false;
                } else if (TextUtils.isEmpty(etMobile
                .getText()
                .toString())) {
                UIUtils.toast(mContext, "请填写联系方式");
                return false;
                } else if (TextUtils.isEmpty(paramBean.province)) {
                UIUtils.toast(mContext, "请选择地区");
                return false;
                } else if (TextUtils.isEmpty(etAddress
                .getText()
                .toString())) {
                UIUtils.toast(mContext, "请填写详细地址");
                return false;
                } else if (TextUtils.isEmpty(tvDevName
                .getText()
                .toString())) {
                UIUtils.toast(mContext, "请选择开发人员");
                return false;
    }

// 是否自主研发，0是，1否
if (rbCustomerSource0.isChecked()) {
paramBean.is_customer_source = "0";
}
if (rbCustomerSource1.isChecked()) {
paramBean.is_customer_source = "1";
}

paramBean.setChannelname(etChannelname
.getText()
.toString());
//        paramBean.setLogname(etLogname.getText()
//                                      .toString());
paramBean.setClerkname(etClerkname
.getText()
.toString());
paramBean.setMobile(etMobile
.getText()
.toString());
paramBean.setAddress(etAddress
.getText()
.toString());

return true;
        }


        @Override
        public void reqCreate() {
            showProgressDialog("数据加载中");
            presenter.reqCreate(paramBean);
        }

        @Override
        public void reqEdit() {
            showProgressDialog("数据加载中");
            presenter.reqEdit(paramBean);
        }

        @Override
         public void onReqSucc(AiyouyunResultEntity entity) {
            dissmissProgressDialog();
            UIUtils.toast(mContext, "保存成功");
            EventBus
            .getDefault()
            .post(new RefreshEvent(0));
            onBackPressed();
        }

        @Override
        public void onReqFail(String msg) {
            UIUtils.toast(mContext, msg);
            dissmissProgressDialog();
        }

        @Override
        public void onSelectClerk(ClerkSelectEvent event) {

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
