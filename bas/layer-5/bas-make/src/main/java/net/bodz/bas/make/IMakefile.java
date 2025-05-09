package net.bodz.bas.make;

import java.util.List;

public interface IMakefile {

    <T extends IMakeTarget> List<IMakeRule<T>> getDeclaredRules(T target);

    default <T extends IMakeTarget> List<IMakeRule<T>> getRules(T target) {
        List<IMakeRule<T>> declaredRules = getDeclaredRules(target);
        List<IMakeRule<T>> usefulRules = getDeclaredRules(target);
ForRule:
        for (IMakeRule<T> rule : declaredRules) {
            for (IMakeTarget source : rule.getSources())
                if (!canMake(source))
                    continue ForRule;
            usefulRules.add(rule);
        }
        return usefulRules;
    }

    default <T extends IMakeTarget> IMakeRule<T> getDefaultRule(T target) {
        List<IMakeRule<T>> declaredRules = getDeclaredRules(target);
ForRule:
        for (IMakeRule<T> rule : declaredRules) {
            for (IMakeTarget source : rule.getSources())
                if (!canMake(source))
                    continue ForRule;
            return rule;
        }
        return null;
    }

    default boolean canMake(IMakeTarget target) {
        return getDefaultRule(target) != null;
    }

}
