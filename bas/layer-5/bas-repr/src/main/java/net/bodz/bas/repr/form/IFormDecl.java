package net.bodz.bas.repr.form;

import java.util.Collection;

import net.bodz.bas.c.type.TypeExtras;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.Volatile;
import net.bodz.bas.meta.stereo.IMetadata;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.ui.dom1.IUiElement;

public interface IFormDecl
        extends IMetadata, IUiElement {

    IFieldDecl getFieldDecl(String name);

    /**
     * @see FieldCategory#groupByCategory(Iterable)
     */
    Collection<IFieldDecl> getFieldDecls();

    Collection<IFieldDecl> getFieldDecls(IFieldDeclFilter filter);

    Collection<FieldDeclGroup> getFieldGroups();

    Collection<FieldDeclGroup> getFieldGroups(IFieldDeclFilter filter);

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
