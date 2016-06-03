package potato.demo.mvp.jiongtulist;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link JiongListPresenter}.
 */
@Module
public class JiongListPresenterModule {

    private final JiongListContract.View mView;
    private final long mSectionId;

    public JiongListPresenterModule(JiongListContract.View view, long sectionId) {
        mView = view;
        mSectionId = sectionId;
    }

    @Provides
    JiongListContract.View provideJiongListContractView() {
        return mView;
    }

    @Provides
    long provideSectionId() {
        return mSectionId;
    }
}
