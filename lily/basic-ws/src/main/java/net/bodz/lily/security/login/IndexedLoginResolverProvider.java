package net.bodz.lily.security.login;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.t.object.IReloadable;

public class IndexedLoginResolverProvider
        implements
            ILoginResolverProvider,
            IReloadable {

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
            Constructor<?>[] ctors = type.getConstructors();
            if (ctors.length > 1)
                throw new IllegalUsageException("type " + type + " has more than one constructor.");
            if (ctors.length == 0)
                throw new IllegalUsageException("type " + type + " has no constructor.");
            Constructor<?> ctor = ctors[0];

            ILoginResolver instance;
            try {
                int n = ctor.getParameterCount();
                Class<?>[] argTypes = ctor.getParameterTypes();
                Object[] args = new Object[n];

                for (int i = 0; i < n; i++) {
                    Class<?> argType = argTypes[i];
                    if (DataContext.class.isAssignableFrom(argType))
                        args[i] = dataContext;
                    else
                        throw new IllegalArgumentException("unknown constructor arg type: " + argType);
                }
                instance = (ILoginResolver) ctor.newInstance(args);
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
