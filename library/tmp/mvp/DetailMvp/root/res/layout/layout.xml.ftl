<?xml version="1.0" encoding="utf-8"?>

<android.support.design.widget.CoordinatorLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

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
                        android:text="客户详情"
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

        <ScrollView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_weight="2"
                android:paddingBottom="5dp"
                android:scrollbars="none"
        >

            <LinearLayout
                    android:id="@+id/container"
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
                            android:text="基本信息"
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
                                android:text="手机号"
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
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                    >

                        <TextView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:gravity="center"
                                android:text="病历号"
                                android:textColor="@color/ifsee_gray_txt_333"
                                android:textSize="@dimen/txt_normal_15"
                        />

                        <TextView
                                android:id="@+id/tv_bingli_code"
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
                            android:text="指派服务人员"
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
                                android:text="就诊状态"
                                android:textColor="@color/ifsee_gray_txt_333"
                                android:textSize="@dimen/txt_normal_15"
                        />

                        <TextView
                                android:id="@+id/tv_jiuzhen_status"
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
                                android:id="@+id/tv_zxs"
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="10dp"
                                android:layout_weight="1"
                                android:background="@null"
                                android:gravity="right"
                                android:singleLine="true"
                                android:text="咨询师"
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
                                android:text="科室"
                                android:textColor="@color/ifsee_gray_txt_333"
                                android:textSize="@dimen/txt_normal_15"
                        />

                        <TextView
                                android:id="@+id/tv_keshi"
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
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                    >

                        <TextView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:gravity="center"
                                android:text="项目"
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
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal"
                    >

                        <TextView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:gravity="center"
                                android:text="分诊接待"
                                android:textColor="@color/ifsee_gray_txt_333"
                                android:textSize="@dimen/txt_normal_15"
                        />

                        <TextView
                                android:id="@+id/tv_fenzhen_clerk"
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

                </LinearLayout>

            </LinearLayout>
        </ScrollView>

    </LinearLayout>

</android.support.design.widget.CoordinatorLayout>
