package net.bodz.bas.tf;

import net.bodz.bas.tf.std.*;

public class CalcPreferredId {

    static Class<?>[] interfaceClasses = {
            //
            IAttributes.class, //
            IClassifier.class, //
            ICommonTypeFeatures.class, //
            IFormatter.class, //
            IInstanceStore.class, //
            IParser.class, //
            ISampleGenerator.class, //
            ISearcher.class, //
            ITextForm.class, //
            IValidator.class, //
    };

    public static void main(String[] args) {
        // Id-hash
        for (Class<?> iface : interfaceClasses) {
            String name = iface.getSimpleName();
            System.out.println("int typeFeatureIndex = " + name.hashCode() + "; // " + name);
        }
        System.out.println();

        // Index-Map
        System.out.println("static final Map<Class<?>, Integer> commonTypeFeaturesIndex;");
        System.out.println("static {");
        System.out.println("    commonTypeFeaturesIndex = new HashMap<Class<?>, Integer>();");
        for (Class<?> iface : interfaceClasses) {
            String name = iface.getSimpleName();
            System.out.println("    commonTypeFeaturesIndex.put(" + name + ".class, " + name + ".typeFeatureIndex);");
        }
        System.out.println("}");
        System.out.println();

        // Switch table
        for (Class<?> iface : interfaceClasses) {
            String name = iface.getSimpleName();
            System.out.println("    case " + name + ".typeFeatureIndex: ");
        }
    }

}
