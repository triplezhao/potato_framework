package potato.demo.mvp.tmp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import potato.demo.R;
import potato.demo.chips.base.BaseFragment;

public class JiongCFragment extends BaseFragment implements JiongC.V {

    public static final String TAG = JiongCFragment.class.getSimpleName();

    @Inject JiongCPresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_jiong_c, container, false);

        ButterKnife.bind(this, view);

        DaggerJiongC_C.builder().module(new JiongC.Module(this)).build().inject(this);

        presenter.start();

        return view;
    }

    @Override
    public void render(Object obj) {

    }

    @Override
    public void onClick(View v) {

    }

}