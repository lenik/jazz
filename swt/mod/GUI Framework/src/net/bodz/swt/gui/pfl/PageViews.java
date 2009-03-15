package net.bodz.swt.gui.pfl;

import java.lang.reflect.Constructor;

import net.bodz.bas.lang.err.ReflectException;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.types.TypeHierMap;

import org.eclipse.swt.widgets.Composite;

public class PageViews {

    private static final TypeHierMap<Class<? extends PageView>> map;
    static {
        map = new TypeHierMap<Class<? extends PageView>>();
    }

    private static boolean                                      nooverwrite = false;

    public static void registerPageView(Class<? extends PageBean> beanType,
            Class<? extends PageView> viewType) {
        if (beanType == null)
            throw new NullPointerException("beanType");
        if (viewType == null)
            throw new NullPointerException("viewType");
        if (nooverwrite && map.containsKey(beanType))
            throw new IllegalArgumentException("beanType " + beanType
                    + " has already been registered. ");
        map.put(beanType, viewType);
    }

    /**
     * @return <code>null</code> if no corresponding {@link PageView} type
     *         registered for this bean type.
     * @throws NullPointerException
     *             if <code>bean</code> is <code>null</code>.
     */
    public static PageView createView(PageBean bean) throws ReflectException {
        if (bean == null)
            throw new NullPointerException("bean");
        Class<? extends PageBean> beanType = bean.getClass();
        Class<? extends PageView> viewType = map.floor(beanType);
        if (viewType == null)
            return null;
        Constructor<? extends PageView> ctor = Reflects.getConstructor(
                viewType, Composite.class, int.class);
        PageView view;
        try {
            view = Reflects.newInstance(ctor);
        } catch (ReflectException e) {
            throw e;
        }
        view.setBean(bean);
        return view;
    }

}
