package potato.demo.mvp.jiongtulist;


import dagger.Component;
import potato.demo.mvp.util.FragmentScoped;

/**
 * <p/>
 * Because this component depends on the {@link JiongListPresenterModule}, which is a singleton, a
 * scope must be specified. All fragment components use a custom scope for this purpose.
 */
@FragmentScoped
@Component(modules = JiongListPresenterModule.class)
public interface JiongListComponent {

    void inject(JiongListFragment jiongListFragment);
}

