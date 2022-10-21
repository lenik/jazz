package net.bodz.bas.repr.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.c.type.TypeExtras;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.Volatile;
import net.bodz.bas.meta.stereo.IMetadata;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.ui.dom1.IUiElement;

public interface IFormDecl
        extends
            IMetadata,
            IUiElement {

    IFormProperty getProperty(String name);

    /**
     * @see PropertyCategory#groupByCategory(Iterable)
     */
    Collection<IFormProperty> getProperties();

    Collection<IFormProperty> getProperties(PropertyFilter filter);

    Collection<PropertyGroup> getPropertyGroups();

    Collection<PropertyGroup> getPropertyGroups(PropertyFilter filter);

    List<PropertyChain> resolvePattern(String pathPattern)
            throws NoSuchPropertyException, ParseException;

    default List<PropertyChain> resolvePatterns(String... pathPatterns)
            throws NoSuchPropertyException, ParseException {
        return resolvePatterns(Arrays.asList(pathPatterns));
    }

    default List<PropertyChain> resolvePatterns(Collection<String> pathPatterns)
            throws NoSuchPropertyException, ParseException {
        List<PropertyChain> list = new ArrayList<>();
        for (String path : pathPatterns) {
            for (PropertyChain chain : resolvePattern(path))
                list.add(chain);
        }
        return list;
    }

    default Map<String, PropertyChain> resolvePatternsToMap(Collection<String> pathProperties)
            throws NoSuchPropertyException, ParseException {
        Map<String, PropertyChain> map = new LinkedHashMap<>();
        for (PropertyChain chain : resolvePatterns(pathProperties))
            map.put(chain.getPath(), chain);
        return map;
    }

    class fn {

        static FormDeclBuilder builder = new FormDeclBuilder();

        public static IFormDecl forClass(Class<?> clazz) {
            IType type = PotatoTypes.getInstance().loadType(clazz);
            boolean isVolatile = type.isAnnotationPresent(Volatile.class);
            TypeExtras extras = TypeExtras.of(clazz);
            IFormDecl formDecl = extras.getFeature(IFormDecl.class);
            if (formDecl == null || isVolatile) {
                try {
                    formDecl = builder.build(type);
                } catch (ParseException e) {
                    throw new IllegalUsageError(e.getMessage(), e);
                }
                extras.setFeature(IFormDecl.class, formDecl);
            }
            return formDecl;
        }

    }

}
