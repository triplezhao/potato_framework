package potato.demo.mvp.splash;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.app.GlobleConstant;
import potato.demo.chips.base.BaseActivity;
import potato.demo.chips.common.PageCtrl;
import potato.demo.chips.util.SPUtils;

public class SplashActivity extends BaseActivity implements Splash.V {

    public static final String TAG = SplashActivity.class.getSimpleName();
    @Bind(R.id.splash_image)
    ImageView mSplashImage;

    @Bind(R.id.splash_version_name)
    TextView mVersionName;

    @Bind(R.id.splash_copyright)
    TextView mCopyright;
    @Inject SplashPresenter presenter;
    private SplashActivity context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        context = this;
        DaggerSplash_C.builder().module(new Splash.Module(this)).build().inject(this);
        init();
    }


    @Override
    public void onClick(View v) {


    }


    public void init() {

        String versionName = null;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String splash_copyright =  context.getResources().getString(R.string.splash_copyright);
        mCopyright.setText(splash_copyright);
        mVersionName.setText(versionName);
        mSplashImage.setImageResource(R.drawable.morning);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.splash);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                navigateToHomePage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mSplashImage.startAnimation(animation);
    }

    @Override
    public void navigateToHomePage() {
        if(SPUtils.getBoolean(mContext, GlobleConstant.sp_isfirst,true)){
            PageCtrl.start2GuideActivity(mContext);
        }else{
            PageCtrl.start2MainActivity(mContext);
        }
    }
}
