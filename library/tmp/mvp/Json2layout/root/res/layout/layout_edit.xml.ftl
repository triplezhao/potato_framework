<#assign text>${json}</#assign>
<#assign json=text?eval />
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:android="http://schemas.android.com/apk/res/android">

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
            <#list json as item>
                <#if item.type == "0">
                    <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/item_h"
                            android:gravity="center_vertical"
                            android:orientation="horizontal">

                        <TextView
                                android:layout_width="match_parent"
                                android:layout_height="wrap_content"
                                android:layout_marginLeft="5dp"
                                android:layout_weight="1"
                                android:background="@null"
                                android:singleLine="true"
                                android:text="${item.title}"
                                android:textColor="@color/ifsee_gray_txt_555"
                                android:textSize="@dimen/txt_normal_14"
                        />
                    </LinearLayout>
                    <View
                            android:layout_width="match_parent"
                            android:layout_height="1px"
                            android:background="@color/ifsee_divider"/>

                    <#elseif item.type == "2">
                        <LinearLayout
                                android:layout_width="match_parent"
                                android:layout_height="@dimen/item_h"
                                android:gravity="center_vertical"
                                android:orientation="horizontal"
                                android:paddingLeft="5dp">

                            <LinearLayout
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content">

                                <TextView
                                        android:layout_width="wrap_content"
                                        android:layout_height="wrap_content"
                                        android:text="${item.title}"
                                        android:textColor="@color/ifsee_gray_txt_333"
                                        android:textSize="@dimen/txt_normal_15"
                                />
                            </LinearLayout>

                            <ifsee.aiyouyun.chips.views.ClearEditText
                                    android:id="@+id/et_${item.vid}"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_marginRight="10dp"
                                    android:layout_weight="1"
                                    android:background="@null"
                                    android:drawablePadding="10dp"
                                    android:gravity="right"
                                    android:hint="${item.hint}"
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

                        <#elseif item.type == "3">
                        <LinearLayout
                                android:id="@+id/ll_${item.vid}"
                                android:layout_width="match_parent"
                                android:layout_height="@dimen/item_h"
                                android:gravity="center_vertical"
                                android:orientation="horizontal"
                                android:paddingLeft="5dp"
                        >

                            <TextView
                                    android:layout_width="wrap_content"
                                    android:layout_height="wrap_content"
                                    android:text="${item.title}"
                                    android:textColor="@color/ifsee_gray_txt_333"
                                    android:textSize="@dimen/txt_normal_15"
                            />

                            <TextView
                                    android:id="@+id/$tv_{item.vid}"
                                    android:layout_width="match_parent"
                                    android:layout_height="wrap_content"
                                    android:layout_marginLeft="10dp"
                                    android:layout_weight="1"
                                    android:gravity="right"
                                    android:hint="${item.hint}"
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
                        <#else>
                </#if>
            </#list>
            <!--分割线-->
            <!--type输入框-->
            <!--type选择框-->
            <!--typeRadio选择-->
            <!--type开关-->

        </LinearLayout>
    </ScrollView>


</LinearLayout>

