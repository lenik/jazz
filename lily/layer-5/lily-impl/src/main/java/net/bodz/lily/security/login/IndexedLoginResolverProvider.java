package net.bodz.lily.security.login;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.t.object.IReloadable;

public class IndexedLoginResolverProvider
        implements ILoginResolverProvider, IReloadable {

    DataContext dataContext;
    List<ILoginResolver> resolvers = new ArrayList<>();

    public IndexedLoginResolverProvider(DataContext dataContext) {
        this.dataContext = dataContext;
        reload();
    }

    @Override
    public synchronized void reload()
            throws LoadException {
        resolvers.clear();
        for (Class<? extends ILoginResolver> type : IndexedTypes.list(ILoginResolver.class, false)) {
            // Constructor<?>[] ctors = type.getConstructors();
            ILoginResolver instance;
            try {
                if (DataBackedLoginResolver.class.isAssignableFrom(type)) {
                    Constructor<? extends ILoginResolver> ctor = type.getConstructor(DataContext.class);
                    instance = ctor.newInstance(dataContext);
                } else {
                    instance = type.newInstance();
                }
            } catch (ReflectiveOperationException e) {
                throw new LoadException(e.getMessage(), e);
            }
            resolvers.add(instance);
        }
    }

    @Override
    public List<ILoginResolver> getResolvers() {
        return resolvers;
    }

}
