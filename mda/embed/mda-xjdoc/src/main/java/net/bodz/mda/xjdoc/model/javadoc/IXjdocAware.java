package net.bodz.mda.xjdoc.model.javadoc;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map.Entry;

import net.bodz.bas.meta.bean.Internal;
import net.bodz.mda.xjdoc.IXjdocProvider;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.IElementDoc;

public interface IXjdocAware {

    @Internal
    IElementDoc getXjdoc();

    void setXjdoc(IElementDoc xjdoc);

    class fn {

        public static String injectFields(Class<?> clazz, boolean includeNonPublic) {
            return injectFields(Xjdocs.getDefaultProvider(), clazz, includeNonPublic);
        }

        public static String injectFields(IXjdocProvider xjdocLoader, Class<?> clazz, boolean includeNonPublic) {
            int modifiers = clazz.getModifiers();
            if (!(includeNonPublic || Modifier.isPublic(modifiers)))
                return "Non-public: " + clazz;

            ClassDoc classDoc = xjdocLoader.getClassDoc(clazz);

            for (Entry<String, FieldDoc> entry : classDoc.getFieldDocs().entrySet()) {
                String fieldName = entry.getKey();
                FieldDoc fieldDoc = entry.getValue();

                Field field;
                try {
                    field = clazz.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    return "No such field: " + fieldName;
                }

                modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers))
                    return "Non-static: " + field;

                if (!(includeNonPublic || Modifier.isPublic(modifiers)))
                    // return "Non-public: " + field;
                    continue;

                Class<?> fieldType = field.getType();
                if (IXjdocAware.class.isAssignableFrom(fieldType))
                    try {
                        IXjdocAware xjdocAware = (IXjdocAware) field.get(null);
                        xjdocAware.setXjdoc(fieldDoc);
                    } catch (IllegalAccessException e) {
                        return "Can't access field: " + e.getMessage();
                    }
            } // for fieldDocs
            return null;
        }

    }

}
