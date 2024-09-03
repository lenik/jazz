package net.bodz.lily.security.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.lily.security.auth.ILoginManagerProvider;

public class LoginManagers {

    static List<ILoginManagerProvider> providers;

    static {
        providers = new ArrayList<>();
        for (ILoginManagerProvider provider : ServiceLoader.load(ILoginManagerProvider.class)) {
            providers.add(provider);
        }
        Collections.sort(providers, PriorityComparator.INSTANCE);
    }

    public static ILoginManager requireLoginManager() {
        ILoginManager loginManager = getLoginManager();
        if (loginManager == null)
            throw new IllegalStateException("No available login manager");
        return loginManager;
    }

    public static ILoginManager getLoginManager() {
        for (ILoginManagerProvider provider : providers) {
            ILoginManager loginManager = provider.getLoginManager();
            if (loginManager != null)
                return loginManager;
        }
        return null;
    }

}
