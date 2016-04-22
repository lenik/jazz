package net.bodz.bas.ui.model.action;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.TypePoMap;

public class ActionIndex {

    public static TypePoMap<List<IAction>> clsActions;
    public static List<IAction> generalActions;

    static {
        clsActions = new TypePoMap<>();
        generalActions = new ArrayList<>();

        for (IAction action : ServiceLoader.load(IAction.class)) {
            Class<?> targetClass = action.getTargetClass();
            if (targetClass == void.class) {
                generalActions.add(action);
                continue;
            }

            List<IAction> list = clsActions.get(targetClass);
            if (list == null)
                clsActions.put(targetClass, list = new ArrayList<IAction>());

            list.add(action);
        }
    }

}
