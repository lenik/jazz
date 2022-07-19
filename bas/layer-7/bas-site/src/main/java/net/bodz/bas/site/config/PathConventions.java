package net.bodz.bas.site.config;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.repr.path.PathToken;

public class PathConventions {

    public static String getPathToken(Class<?> clazz) {
        Set<String> set = getPathTokens(clazz);
        Iterator<String> iterator = set.iterator();
        if (!iterator.hasNext())
            return null;
        String first = iterator.next();
        return first;
    }

    public static Set<String> getPathTokens(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");

        Set<String> tokens = new LinkedHashSet<String>();

        PathToken aPathToken = clazz.getAnnotation(PathToken.class);
        if (aPathToken != null)
            tokens.add(StringArray.join("/", aPathToken.value()));

        Table aTable = clazz.getAnnotation(Table.class);
        if (aTable != null) {
            String tableName = aTable.name();
            tokens.add(tableName);
        }
        tokens.add(StringId.HYPHEN.breakCamel(clazz.getSimpleName()));

        return tokens;
    }

}
