package net.bodz.bas.typer;

import java.security.MessageDigest;

import net.bodz.bas.typer.std.*;

public class CalcPreferredId {

    static Class<?>[] interfaceClasses = {
            //
            IAttributes.class, //
            IClassifier.class, //
            IBasicTyperFamily.class, //
            IFormatter.class, //
            IInstanceStore.class, //
            IParser.class, //
            ISampleGenerator.class, //
            ISearcher.class, //
            ITextForm.class, //
            IValidator.class, //
    };

    public static void main(String[] args)
            throws Exception {
        MessageDigest SHA1 = MessageDigest.getInstance("SHA1");

        // Id-hash
        for (Class<?> iface : interfaceClasses) {
            String name = iface.getSimpleName();
            // int hashCode = name.hashCode();

            byte[] digest = SHA1.digest(name.getBytes("utf-8"));
            int a = digest[0] & 0xff;
            int b = digest[1] & 0xff;
            int c = digest[2] & 0xff;
            int d = digest[3] & 0xff;
            int sha1 = (a << 24) | (b << 16) | (c << 8) | d;
            System.out.printf("int typerIndex = 0x%08x; // %s\n", //
                    sha1, name);
        }
        System.out.println();

        // Index-Map
        System.out.println("static final Map<Class<?>, Integer> commonTypersIndex;");
        System.out.println("static {");
        System.out.println("    commonTypersIndex = new HashMap<Class<?>, Integer>();");
        for (Class<?> iface : interfaceClasses) {
            String name = iface.getSimpleName();
            System.out.println("    commonTypersIndex.put(" + name + ".class, " + name + ".typerIndex);");
        }
        System.out.println("}");
        System.out.println();

        // Switch table
        for (Class<?> iface : interfaceClasses) {
            String name = iface.getSimpleName();
            System.out.println("    case " + name + ".typerIndex: ");
        }
    }

}
