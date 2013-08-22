package net.bodz.bas.mf;

import net.bodz.bas.mf.std.*;

public class CalcPreferredId {

    static Class<?>[] interfaceClasses = {
            //
            IAttributes.class, //
            IClassifier.class, //
            ICommonMdaFeatures.class, //
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
            System.out.println("int mdaFeatureIndex = " + name.hashCode() + "; // " + name);
        }
        System.out.println();

        // Index-Map
        System.out.println("static final Map<Class<?>, Integer> commonMdaFeaturesIndex;");
        System.out.println("static {");
        System.out.println("    commonMdaFeaturesIndex = new HashMap<Class<?>, Integer>();");
        for (Class<?> iface : interfaceClasses) {
            String name = iface.getSimpleName();
            System.out.println("    commonMdaFeaturesIndex.put(" + name + ".class, " + name + ".mdaFeatureIndex);");
        }
        System.out.println("}");
        System.out.println();

        // Switch table
        for (Class<?> iface : interfaceClasses) {
            String name = iface.getSimpleName();
            System.out.println("    case " + name + ".mdaFeatureIndex: ");
        }
    }

}
