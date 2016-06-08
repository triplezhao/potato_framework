package potato.demo.mvp.tmp;

import javax.inject.Inject;

final class JiongBPresenter implements JiongB.P {


    private JiongB.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    JiongBPresenter(
            JiongB.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void loadData() {


    }
}