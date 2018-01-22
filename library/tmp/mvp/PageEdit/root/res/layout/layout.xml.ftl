<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/page_bg">

    <android.support.design.widget.AppBarLayout
            android:id="@+id/appbar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="@dimen/header_h"
                android:background="?attr/colorPrimary"
                app:contentInsetLeft="0dp"
                app:contentInsetStart="0dp"
                app:layout_scrollFlags="enterAlways"
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
        >

            <RelativeLayout
                    android:id="@+id/rl_header"
                    android:layout_width="fill_parent"
                    android:layout_height="fill_parent"
                    android:background="@color/ifsee_blue"
                    android:gravity="center_vertical">

                <TextView
                        android:id="@+id/tv_title"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_centerInParent="true"
                        android:ellipsize="end"
                        android:singleLine="true"
                        android:text="新增店家"
                        android:textColor="@color/potato_white"
                        android:textSize="@dimen/txt_max"/>

                <ImageView
                        android:id="@+id/iv_back"
                        style="@style/BackWhite"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_alignParentLeft="true"
                        android:layout_centerVertical="true"
                />

                <ImageView
                        android:id="@+id/iv_setting"
                        style="@style/BackBlack"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_alignParentRight="true"
                        android:layout_centerVertical="true"
                        android:src="@drawable/setting"
                        android:visibility="gone"
                />

            </RelativeLayout>
        </android.support.v7.widget.Toolbar>
    </android.support.design.widget.AppBarLayout>

    <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="10dp"
                android:layout_weight="2"
                android:orientation="vertical">

            <ScrollView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:layout_weight="2"
                    android:background="@color/potato_white"
                    android:scrollbars="none"
            >

                <LinearLayout
                        android:id="@+id/container"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:orientation="vertical"
                >

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                            android:paddingLeft="10dp">

                        <LinearLayout
                                android:layout_width="85dp"
                                android:layout_height="wrap_content">

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="*"
                                    android:textColor="@color/red"
                                    android:textSize="@dimen/txt_max_18"
                            />

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="店家名称"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />
                        </LinearLayout>

                        <ifsee.aiyouyun.chips.views.ClearEditText
                                android:id="@+id/et_channelname"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="10dp"
                                android:layout_marginRight="10dp"
                                android:layout_weight="1"
                                android:background="@null"
                                android:hint="请输入店家名称"
                                android:inputType="textPersonName"
                                android:maxLines="1"
                                android:singleLine="true"
                                android:textColor="@color/ifsee_gray_txt_666"
                                android:textColorHint="@color/ifsee_gray_txt_888"
                                android:textCursorDrawable="@null"
                                android:textSize="@dimen/txt_normal_14"
                        />
                    </LinearLayout>



                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"
                    />

                    <View
                            android:layout_width="match_parent"
                            android:layout_height="14dp"
                            android:background="@color/page_bg"
                    />

                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"/>

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                            android:paddingLeft="10dp">

                        <LinearLayout
                                android:layout_width="85dp"
                                android:layout_height="wrap_content">

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="*"
                                    android:textColor="@color/red"
                                    android:textSize="@dimen/txt_max_18"
                            />

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="老板姓名"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />
                        </LinearLayout>

                        <ifsee.aiyouyun.chips.views.ClearEditText
                                android:id="@+id/et_clerkname"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="10dp"
                                android:layout_marginRight="10dp"
                                android:layout_weight="1"
                                android:background="@null"
                                android:hint="请输入老板姓名"
                                android:inputType="textPersonName"
                                android:maxLines="1"
                                android:singleLine="true"
                                android:textColor="@color/ifsee_gray_txt_666"
                                android:textColorHint="@color/ifsee_gray_txt_888"
                                android:textCursorDrawable="@null"
                                android:textSize="@dimen/txt_normal_14"
                        />
                    </LinearLayout>

                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"/>

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                            android:paddingLeft="10dp">

                        <LinearLayout
                                android:layout_width="85dp"
                                android:layout_height="wrap_content">

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="*"
                                    android:textColor="@color/red"
                                    android:textSize="@dimen/txt_max_18"
                            />

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="联系方式"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />
                        </LinearLayout>

                        <ifsee.aiyouyun.chips.views.ClearEditText
                                android:id="@+id/et_mobile"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="10dp"
                                android:layout_marginRight="10dp"
                                android:layout_weight="1"
                                android:background="@null"
                                android:hint="请输入联系方式"
                                android:inputType="phone|number"
                                android:maxLength="11"
                                android:maxLines="1"
                                android:singleLine="true"
                                android:textColor="@color/ifsee_gray_txt_666"
                                android:textColorHint="@color/ifsee_gray_txt_888"
                                android:textCursorDrawable="@null"
                                android:textSize="@dimen/txt_normal_14"
                        />
                    </LinearLayout>

                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"/>

                    <LinearLayout
                            android:id="@+id/ll_diqu"
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                            android:paddingLeft="10dp"
                    >

                        <LinearLayout
                                android:layout_width="85dp"
                                android:layout_height="wrap_content">

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="*"
                                    android:textColor="@color/red"
                                    android:textSize="@dimen/txt_max_18"
                            />

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="店家地址"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />
                        </LinearLayout>

                        <TextView
                                android:id="@+id/tv_diqu"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="10dp"
                                android:layout_weight="1"
                                android:hint="请选择店家地区"
                                android:textColor="@color/ifsee_gray_txt_666"
                                android:textColorHint="@color/ifsee_gray_txt_888"
                                android:textSize="@dimen/txt_normal_14"
                        />

                        <ImageView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:padding="10dp"
                                android:src="@drawable/arrow_right"/>
                    </LinearLayout>

                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"/>

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                            android:paddingLeft="10dp">

                        <LinearLayout
                                android:layout_width="85dp"
                                android:layout_height="wrap_content">

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="*"
                                    android:textColor="@color/red"
                                    android:textSize="@dimen/txt_max_18"
                            />

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="详细地址"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />
                        </LinearLayout>

                        <ifsee.aiyouyun.chips.views.ClearEditText
                                android:id="@+id/et_address"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="10dp"
                                android:layout_marginRight="10dp"
                                android:layout_weight="1"
                                android:background="@null"
                                android:hint="请输入详细地址"
                                android:inputType="textPersonName"
                                android:maxLines="1"
                                android:singleLine="true"
                                android:textColor="@color/ifsee_gray_txt_666"
                                android:textColorHint="@color/ifsee_gray_txt_888"
                                android:textCursorDrawable="@null"
                                android:textSize="@dimen/txt_normal_14"
                        />
                    </LinearLayout>


                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"
                    />

                    <View
                            android:layout_width="match_parent"
                            android:layout_height="14dp"
                            android:background="@color/page_bg"
                    />

                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"/>

                    <RadioGroup
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                            android:paddingLeft="10dp"
                    >

                        <LinearLayout
                                android:layout_width="85dp"
                                android:layout_height="wrap_content">

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="*"
                                    android:textColor="@color/red"
                                    android:textSize="@dimen/txt_max_18"
                            />

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="客户来源"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />
                        </LinearLayout>

                        <RadioButton
                                android:id="@+id/rb_customer_source_0"
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:layout_marginRight="20dp"
                                android:checked="true"
                                android:gravity="center_vertical"
                                android:text="自主开发"
                                android:textColor="@color/ifsee_gray_txt_333"
                                android:textSize="@dimen/txt_normal_15"
                        />

                        <RadioButton
                                android:id="@+id/rb_customer_source_1"
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:text="转介绍"
                                android:textColor="@color/ifsee_gray_txt_333"
                                android:textSize="@dimen/txt_normal_15"
                        />
                    </RadioGroup>

                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"/>

                    <LinearLayout
                            android:id="@+id/ll_zhuanjieshao"
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                            android:paddingLeft="10dp"
                            android:visibility="gone"
                    >


                        <LinearLayout
                                android:layout_width="85dp"
                                android:layout_height="wrap_content">

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="*"
                                    android:textColor="@color/red"
                                    android:textSize="@dimen/txt_max_18"
                            />

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="转介绍店家"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />
                        </LinearLayout>

                        <TextView
                                android:id="@+id/tv_zhuanjieshao_name"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="10dp"
                                android:layout_weight="1"
                                android:hint="请选择转介绍店家"
                                android:textColor="@color/ifsee_gray_txt_666"
                                android:textColorHint="@color/ifsee_gray_txt_888"
                                android:textSize="@dimen/txt_normal_14"
                        />

                        <ImageView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:padding="10dp"
                                android:src="@drawable/arrow_right"/>
                    </LinearLayout>

                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"/>

                    <LinearLayout
                            android:id="@+id/ll_dev"
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                            android:paddingLeft="10dp"
                    >

                        <LinearLayout
                                android:layout_width="100dp"
                                android:layout_height="wrap_content">

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="*"
                                    android:textColor="@color/red"
                                    android:textSize="@dimen/txt_max_18"
                            />

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="店家开发人员"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />
                        </LinearLayout>

                        <TextView
                                android:id="@+id/tv_dev_name"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="10dp"
                                android:layout_weight="1"
                                android:hint="请选择店家开发人员"
                                android:textColor="@color/ifsee_gray_txt_666"
                                android:textColorHint="@color/ifsee_gray_txt_888"
                                android:textSize="@dimen/txt_normal_14"
                        />

                        <ImageView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:padding="10dp"
                                android:src="@drawable/arrow_right"/>
                    </LinearLayout>
                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"/>

                </LinearLayout>
            </ScrollView>


        </LinearLayout>

        <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="@color/potato_white"
                android:orientation="vertical">

            <View
                    android:layout_width="match_parent"
                    android:layout_height="1px"
                    android:background="@color/ifsee_gray_bt_ddd"/>

            <Button
                    android:id="@+id/bt_save"
                    style="@style/BottomButton"
                    android:text="保存"
            />
        </LinearLayout>

    </LinearLayout>

</android.support.design.widget.CoordinatorLayout>
