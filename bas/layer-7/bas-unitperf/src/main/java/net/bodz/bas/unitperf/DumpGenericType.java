package net.bodz.bas.unitperf;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;

public class DumpGenericType {

    public static void dumpGenericDeclaration(String indent, GenericDeclaration gd) {
        if (indent.length() > 20)
            return;
        System.out.println(indent + gd);
        TypeVariable<?>[] tvs = gd.getTypeParameters();
        indent += "  ";
        for (int i = 0; i < tvs.length; i++) {
            TypeVariable<?> tv = tvs[i];
            System.out.printf(indent + "Type-Parameter[%d]: %s\n", i, tv.getName());
            Type[] bounds = tv.getBounds();
            for (int bi = 0; bi < bounds.length; bi++) {
                Type bound = bounds[bi];
                System.out.printf(indent + "  Bound[%d]: %s\n", bi, bound);
                dumpType(indent + "  ", bound);
            }
        }
    }

    static void dumpType(String indent, Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            System.out.println(indent + "  Owner-Type: " + pt.getOwnerType());
            System.out.println(indent + "  Raw-Type: " + pt.getRawType());
            Type[] actuals = pt.getActualTypeArguments();
            for (int a = 0; a < actuals.length; a++) {
                Type actual = actuals[a];
                System.out.printf(indent + "  Actual-Type[%d]: %s\n", a, actual);
                dumpType(indent + "  ", actual);
            }
        }
        if (type instanceof WildcardType) {
            WildcardType wt = (WildcardType) type;
            Type[] lowerBounds = wt.getLowerBounds();
            Type[] upperBounds = wt.getUpperBounds();
            for (int i = 0; i < lowerBounds.length; i++) {
                Type bound = lowerBounds[i];
                System.out.printf(indent + "  Lower[%d]: %s\n", i, bound);
                dumpType(indent + "    ", bound);
            }
            for (int i = 0; i < upperBounds.length; i++) {
                Type bound = upperBounds[i];
                System.out.printf(indent + "  Upper[%d]: %s\n", i, bound);
                dumpType(indent + "    ", bound);
            }
        }
    }

    public static void main(String[] args) {
        dumpGenericDeclaration("", ArrayList.class);
    }

}
