package potato.demo.mvp.splash;

import javax.inject.Inject;

final class SplashPresenter implements Splash.P {


    private Splash.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    SplashPresenter(
            Splash.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }

    @Override
    public void loadData() {


    }
}