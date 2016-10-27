package potato.demo.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import potato.demo.R;
import potato.demo.chips.app.GlobleConstant;
import potato.demo.chips.common.PageCtrl;
import potato.demo.chips.util.SPUtils;

/**
 * Created by admin on 2016/8/19.
 */
public class GuideActivity extends AppIntro {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//
        super.onCreate(savedInstanceState);
        // AppIntro will automatically generate the dots indicator and buttons.
       /* addSlide(first_fragment);
        addSlide(second_fragment);
        addSlide(third_fragment);
        addSlide(fourth_fragment);*/
    /*    addSlide(fourth_fragment);*//*
        addSlide(fourth_fragment);*//*
        addSlide(fourth_fragment);*/

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance("1", "1111111", R.drawable.cheese_1, getResources().getColor(R.color.blue)));
        addSlide(AppIntroFragment.newInstance("2", "22222", R.drawable.cheese_2, getResources().getColor(R.color.blue)));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
//        showSkipButton(false);
//        setProgressButtonEnabled(false);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        SPUtils.putBoolean(GuideActivity.this, GlobleConstant.sp_isfirst,false);
        PageCtrl.start2MainActivity(this);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        SPUtils.putBoolean(GuideActivity.this, GlobleConstant.sp_isfirst,false);
        PageCtrl.start2MainActivity(this);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
