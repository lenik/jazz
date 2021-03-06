package cg;


/**
 * @see net.bodz.bas.t.variant.AbstractTmVariantMap
 */
public class VariantMapGenerator {

    public static void main(String[] args) {
        String[] types = { // "Object", "String", //
        "byte", "short", "int", "long", "float", "double", "boolean", "char", //
                "BigInteger", "BigDecimal", //
                "Date", //
                "URI", "URL", "Path", "File", //
        };

        System.out.println("Interface Declaration: ");
        for (String type : types) {
            String Type = type.substring(0, 1).toUpperCase() + type.substring(1);
            System.out.printf("    %s get%s(String key);\n", Type, Type);
            System.out.printf("    %s get%s(String key, %s defaultValue);\n", type, Type, type);
        }

        System.out.println();
        System.out.println("Default Implementation: ");
        for (String type : types) {
            String Type = type.substring(0, 1).toUpperCase() + type.substring(1);
            System.out.printf("" + //
                    "    @Override\n" + // tTt
                    "    public %s get%s(String key) {\n" + // tT
                    "        Object value = getScalar(key); \n" + //
                    "        if (value == null)\n" + //
                    "            return null; \n" + //
                    "        else\n" + //
                    "            return %sVarConverter.instance.from(value); \n" + // t
                    "    }\n", Type, Type, Type);
            System.out.printf("" + //
                    "    @Override\n" + // tTt
                    "    public %s get%s(String key, %s defaultValue) {\n" + // tTt
                    "        Object value = getScalar(key); \n" + //
                    "        if (value == null && !containsKey(key))\n" + //
                    "            return defaultValue; \n" + //
                    "        try {\n" + //
                    "            return %sVarConverter.instance.from(value); \n" + // t
                    "        } catch (TypeConvertException e) {\n" + //
                    "            return defaultValue; \n" + //
                    "        }\n" + //
                    "    }\n\n", type, Type, type, Type);
        }
    }

}
