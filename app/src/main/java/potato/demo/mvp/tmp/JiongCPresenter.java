package potato.demo.mvp.tmp;

import javax.inject.Inject;

final class JiongCPresenter implements JiongC.P {


    private JiongC.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    JiongCPresenter(
            JiongC.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void loadData() {


    }
}