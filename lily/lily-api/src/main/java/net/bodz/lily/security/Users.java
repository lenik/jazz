package net.bodz.lily.security;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class Users {

    static final List<IUserFactory> userFactories = new ArrayList<>();
    static {
        for (IUserFactory userFactory : ServiceLoader.load(IUserFactory.class))
            userFactories.add(userFactory);
    }

    public static IMutableUser newUser() {
        for (IUserFactory userFactory : userFactories)
            return userFactory.newUser();
        throw new IllegalStateException("no user factory configured");
    }

    public static IMutableGroup newGroup() {
        for (IUserFactory userFactory : userFactories)
            return userFactory.newGroup();
        throw new IllegalStateException("no user factory configured");
    }

}
