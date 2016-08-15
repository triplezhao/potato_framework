<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main_content"
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
            android:layout_height="35dp"
            android:background="?attr/colorPrimary"
            app:layout_scrollFlags="scroll|enterAlways"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
            app:logo="@drawable/ic_fav"
            app:title="Baidu"/>


        <android.support.design.widget.TabLayout
            android:id="@+id/tabs"
            android:layout_width="match_parent"
            android:layout_height="40dp"
            app:tabGravity="fill"
            app:tabIndicatorColor="@color/potato_white"
            app:tabIndicatorHeight="3dp"
            app:tabMode="scrollable"
            app:tabSelectedTextColor="@color/potato_white"/>
    </android.support.design.widget.AppBarLayout>


    <FrameLayout
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@id/toolbar"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <android.support.v4.view.ViewPager
            android:id="@+id/viewpager"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginTop="0dp"/>

        <com.potato.library.view.NormalEmptyView
            android:id="@+id/empty_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_below="@id/rl_header"
            android:visibility="gone">
        </com.potato.library.view.NormalEmptyView>
    </FrameLayout>


</android.support.design.widget.CoordinatorLayout>