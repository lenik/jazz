package net.bodz.bas.ui.model.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import net.bodz.bas.meta.codegen.IndexedTypeLoader;

@IndexedTypeLoader(UiLocationDecl.class)
public class UiLocationIndex {

    static Map<Class<?>, UiLocationDecl> map;
    static List<UiLocationDecl> roots;

    static {
        map = new HashMap<>();
        roots = new ArrayList<>();

        for (UiLocationDecl decl : ServiceLoader.load(UiLocationDecl.class)) {
            map.put(decl.getClass(), decl);
        }

        // Rebuild parent/children relationship.
        for (UiLocationDecl decl : map.values()) {
            Location aLocation = decl.getClass().getAnnotation(Location.class);
            if (aLocation != null)
                for (Class<?> c : aLocation.value()) {
                    UiLocationDecl parent = map.get(c);
                    decl.setParent(parent);
                    parent.addChild(decl);
                }
            else
                roots.add(decl);
        }
    }

    public static UiLocationDecl get(Class<?> locationClass) {
        return map.get(locationClass);
    }

    public static List<UiLocationDecl> getRoots() {
        return roots;
    }

}
