package potato.demo.mvp.tmp;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import potato.demo.R;
import potato.demo.chips.base.BaseActivity;

public class JiongBActivity extends BaseActivity implements JiongB.V {

    public static final String TAG = JiongBActivity.class.getSimpleName();

    @Inject JiongBPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiong_b);
        ButterKnife.bind(this);

        DaggerJiongB_C.builder().module(new JiongB.Module(this)).build().inject(this);

        presenter.start();
    }

    @Override
    public void render(Object obj) {

    }

}