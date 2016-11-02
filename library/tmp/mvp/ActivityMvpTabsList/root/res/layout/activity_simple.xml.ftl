<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto"
                android:layout_width="match_parent"
                android:layout_height="match_parent">

    <RelativeLayout
        android:id="@+id/rl_header"
        android:layout_width="fill_parent"
        android:layout_height="50dip"
        android:background="@color/sticker_blue"
        android:gravity="center_vertical">

        <TextView
            android:id="@+id/tv_title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentLeft="true"
            android:layout_centerVertical="true"
            android:layout_marginLeft="20dp"
            android:ellipsize="end"
            android:singleLine="true"
            android:text="FitTime"
            android:textColor="@color/potato_white"
            android:textSize="@dimen/txt_max"/>

        <ImageView
            android:id="@+id/iv_back"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentRight="true"
            android:layout_centerVertical="true"
            android:onClick="onClick"
            android:padding="10dip"
            android:src="@drawable/ic_add_white_24dp"/>
    </RelativeLayout>

    <LinearLayout
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/rl_header">

        <android.support.design.widget.TabLayout
            android:id="@+id/tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:tabGravity="fill"
            app:tabIndicatorColor="@color/google_red"
            app:tabIndicatorHeight="3dp"
            app:tabMode="scrollable"
            app:tabSelectedTextColor="@color/google_red"/>
    </LinearLayout>

    <FrameLayout
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@id/toolbar">

        <android.support.v4.view.ViewPager
            android:id="@+id/viewpager"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginTop="0dp"/>

    </FrameLayout>

    <com.potato.library.view.NormalEmptyView
        android:id="@+id/empty_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@id/rl_header"
        android:visibility="gone">

    </com.potato.library.view.NormalEmptyView>

    <View
        android:layout_width="match_parent"
        android:layout_height="5dp"
        android:layout_below="@id/toolbar"
        android:background="@drawable/bottom_shadow_bg"/>

    <View
        android:layout_width="match_parent"
        android:layout_height="5dp"
        android:layout_below="@id/rl_header"
        android:background="@drawable/bottom_shadow_bg"/>

</RelativeLayout>
