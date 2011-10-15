package net.bodz.bas.traits;

public class CalcPreferredId {

    static Class<?>[] interfaceClasses = {
            //
            IAttributes.class, //
            IClassifier.class, //
            ICommonTraits.class, //
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
            System.out.println("int traitIndex = " + name.hashCode() + "; // " + name);
        }
        System.out.println();

        // Index-Map
        System.out.println("static final Map<Class<?>, Integer> commonTraitsIndex;");
        System.out.println("static {");
        System.out.println("    commonTraitsIndex = new HashMap<Class<?>, Integer>();");
        for (Class<?> iface : interfaceClasses) {
            String name = iface.getSimpleName();
            System.out.println("    commonTraitsIndex.put(" + name + ".class, " + name + ".traitIndex);");
        }
        System.out.println("}");
        System.out.println();

        // Switch table
        for (Class<?> iface : interfaceClasses) {
            String name = iface.getSimpleName();
            System.out.println("    case " + name + ".traitIndex: ");
        }
    }

}
