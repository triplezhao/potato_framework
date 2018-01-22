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
                        android:text="预约单详情"
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

        <com.potato.library.view.NormalEmptyView
                android:id="@+id/empty_view"
                android:layout_width="fill_parent"
                android:layout_height="fill_parent"
                android:visibility="gone">

        </com.potato.library.view.NormalEmptyView>

        <com.potato.library.view.refresh.PotatoBaseSwipeLayout
                android:id="@+id/swipe_container"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_weight="2">

            <android.support.v4.widget.NestedScrollView
                    android:id="@+id/scroll_container"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:layout_weight="2"
                    android:paddingBottom="5dp"
                    android:scrollbars="none"
            >

                <LinearLayout
                        android:id="@+id/ll_realpage"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:layout_margin="10dp"
                        android:orientation="vertical"
                >

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal">

                        <View
                                android:layout_width="3dp"
                                android:layout_height="match_parent"
                                android:layout_marginBottom="10dp"
                                android:layout_marginTop="10dp"
                                android:background="@color/ifsee_blue"/>

                        <TextView
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="5dp"
                                android:layout_weight="1"
                                android:background="@null"
                                android:singleLine="true"
                                android:text="客户基础信息"
                                android:textColor="@color/ifsee_gray_txt_555"
                                android:textSize="@dimen/txt_normal_14"
                        />
                    </LinearLayout>

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:background="@drawable/corner_bg_white"
                            android:orientation="vertical"
                            android:padding="10dp">

                        <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="@dimen/item_h"
                                android:gravity="center_vertical"
                                android:orientation="horizontal"
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="客户姓名"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_realname"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"

                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                                android:padding="0dp">

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="主手机号"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />


                            <TextView
                                    android:id="@+id/tv_mobile"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:hint="主手机号"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                                android:padding="0dp">

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="副手机号"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />


                            <TextView
                                    android:id="@+id/tv_vice_mobile"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:hint="副手机号"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="客户号"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_cusno"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="微信昵称"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_nickname"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
                                    android:textSize="@dimen/txt_normal_14"
                            />
                        </LinearLayout>

                        <View
                                android:layout_width="match_parent"
                                android:layout_height="1px"
                                android:background="@color/ifsee_divider"/>

                        <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginTop="15dp"
                                android:orientation="horizontal"
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:text="联系地址"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_address"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:maxLines="3"
                                    android:minLines="3"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
                                    android:textSize="@dimen/txt_normal_14"
                            />
                        </LinearLayout>

                    </LinearLayout>


                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal">

                        <View
                                android:layout_width="3dp"
                                android:layout_height="match_parent"
                                android:layout_marginBottom="10dp"
                                android:layout_marginTop="10dp"
                                android:background="@color/ifsee_blue"/>

                        <TextView
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="5dp"
                                android:layout_weight="1"
                                android:background="@null"
                                android:singleLine="true"
                                android:text="预约信息"
                                android:textColor="@color/ifsee_gray_txt_555"
                                android:textSize="@dimen/txt_normal_14"
                        />
                    </LinearLayout>

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:background="@drawable/corner_bg_white"
                            android:orientation="vertical"
                            android:padding="10dp">

                        <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="@dimen/item_h"
                                android:gravity="center_vertical"
                                android:orientation="horizontal"
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="预约单号"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_ordersn"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"

                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="预约到店时间"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_appointment_time"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"

                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="有意向的医生"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_doctor"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text="name"
                                    android:textColor="@color/ifsee_gray_txt_555"
                                    android:textSize="@dimen/txt_normal_14"
                            />
                        </LinearLayout>

                        <View
                                android:layout_width="match_parent"
                                android:layout_height="1px"
                                android:background="@color/ifsee_divider"/>

                        <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:gravity="center_vertical"
                                android:minHeight="@dimen/item_h"
                                android:orientation="horizontal"
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="有意向的项目"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_project"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:maxLines="2"
                                    android:text="name"
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="有意向的美容师"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_mrs"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="预约单生成时间"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_createtime"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text="name"
                                    android:textColor="@color/ifsee_gray_txt_555"
                                    android:textSize="@dimen/txt_normal_14"
                            />
                        </LinearLayout>

                        <View
                                android:layout_width="match_parent"
                                android:layout_height="1px"
                                android:background="@color/ifsee_divider"/>

                        <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginTop="15dp"
                                android:orientation="horizontal"
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:text="说明"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_remark"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:maxLines="3"
                                    android:minLines="3"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
                                    android:textSize="@dimen/txt_normal_14"
                            />
                        </LinearLayout>

                        <View
                                android:layout_width="match_parent"
                                android:layout_height="1px"
                                android:background="@color/ifsee_divider"/>

                        <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginTop="15dp"
                                android:orientation="horizontal"
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:text="未到店原因"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_reason"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:maxLines="3"
                                    android:minLines="3"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
                                    android:textSize="@dimen/txt_normal_14"
                            />
                        </LinearLayout>

                    </LinearLayout>

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal">

                        <View
                                android:layout_width="3dp"
                                android:layout_height="match_parent"
                                android:layout_marginBottom="10dp"
                                android:layout_marginTop="10dp"
                                android:background="@color/ifsee_blue"/>

                        <TextView
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="5dp"
                                android:layout_weight="1"
                                android:background="@null"
                                android:singleLine="true"
                                android:text="归属信息"
                                android:textColor="@color/ifsee_gray_txt_555"
                                android:textSize="@dimen/txt_normal_14"
                        />
                    </LinearLayout>

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:background="@drawable/corner_bg_white"
                            android:orientation="vertical"
                            android:padding="10dp">

                        <View
                                android:layout_width="match_parent"
                                android:layout_height="1px"
                                android:background="@color/ifsee_divider"/>

                        <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="@dimen/item_h"
                                android:gravity="center_vertical"
                                android:orientation="horizontal"
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="咨询师"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_zxsname"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="网电咨询"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_wd_zxs"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="客服"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_kfname"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="开发人员"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_dev_user"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="店家"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_channelname"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
                                    android:textSize="@dimen/txt_normal_14"
                            />
                        </LinearLayout>

                    </LinearLayout>

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal">

                        <View
                                android:layout_width="3dp"
                                android:layout_height="match_parent"
                                android:layout_marginBottom="10dp"
                                android:layout_marginTop="10dp"
                                android:background="@color/ifsee_blue"/>

                        <TextView
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="5dp"
                                android:layout_weight="1"
                                android:background="@null"
                                android:singleLine="true"
                                android:text="其他"
                                android:textColor="@color/ifsee_gray_txt_555"
                                android:textSize="@dimen/txt_normal_14"
                        />
                    </LinearLayout>

                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:background="@drawable/corner_bg_white"
                            android:orientation="vertical"
                            android:padding="10dp">

                        <View
                                android:layout_width="match_parent"
                                android:layout_height="1px"
                                android:background="@color/ifsee_divider"/>

                        <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="@dimen/item_h"
                                android:gravity="center_vertical"
                                android:orientation="horizontal"
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="客户来源"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_khfrom"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="开发渠道"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_dev_channel"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="信息来源"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_sourceid"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="是否需要专车"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_needcar"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="到店当日是否生成待分配就诊记录"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_istriage"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
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
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:gravity="center"
                                    android:text="预约人"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/tv_yyr_name"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:gravity="right"
                                    android:singleLine="true"
                                    android:text=""
                                    android:textColor="@color/ifsee_gray_txt_555"
                                    android:textSize="@dimen/txt_normal_14"
                            />
                        </LinearLayout>

                    </LinearLayout>
                </LinearLayout>
            </android.support.v4.widget.NestedScrollView>
        </com.potato.library.view.refresh.PotatoBaseSwipeLayout>

        <Button
                android:id="@+id/bt_edit"
                style="@style/BottomButton"
                android:text="编辑"/>
    </LinearLayout>

</android.support.design.widget.CoordinatorLayout>
