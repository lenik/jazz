package net.bodz.lily.security;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.junit.Assert;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.t.variant.IMutableVariantMap;
import net.bodz.bas.t.variant.MutableVariantMap;
import net.bodz.lily.security.User;

public class UserTest
        extends Assert {

    public static void main(String[] args)
            throws LoaderException {
        Map<String, Object> map = new HashMap<>();
        IMutableVariantMap<String> vmap = MutableVariantMap.fromMap(map);
        map.put("codeName", "Lenik");
        map.put("accessMode", 123);
        map.put("label", "Lenik Xie");
        map.put("primaryGroup.id", "700");
        map.put("primaryGroup.label", "Group 1");

        User user = new User();
        user.readObject(vmap);
        ReflectionToStringBuilder b = new ReflectionToStringBuilder(user);
        System.out.println(b);
    }

}
