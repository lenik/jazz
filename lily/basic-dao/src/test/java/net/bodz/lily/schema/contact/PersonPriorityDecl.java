package net.bodz.lily.schema.contact;

import net.bodz.bas.c.type.TypeChain;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.provider.bean.BeanTypeProvider;
import net.bodz.lily.concrete.StructRow;

public class PersonPriorityDecl {

    void dumpGroups(Class<?> javaClass) {
        for (Class<?> decl : TypeChain.supersFromRoot(javaClass)) {
            System.out.println("\nDECL: " + decl);
            if (! StructRow.class.isAssignableFrom(decl))
                continue;

            IType type = BeanTypeProvider.getInstance().getType(decl);
            for (IProperty property : type.getProperties()) {
                Class<?> propDecl = property.getDeclaringClass();
                System.out.printf("[%s] %s\n", propDecl.getSimpleName(), //
                        property.getName());
            }
        }
    }

    public static void main(String[] args) {
        Class<?> c = Person.class;
        new PersonPriorityDecl().dumpGroups(c);
    }

}
