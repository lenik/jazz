package net.bodz.bas.c.java.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.regex.IPartProcessor;
import net.bodz.bas.c.java.util.regex.TextPrepByParts;

public class ArraysCG {

    static List<TypeDef> elmtypes;
    static List<TypeDef> algtypes;
    static List<TypeDef> inttypes;
    static {
        elmtypes = new ArrayList<TypeDef>();
        elmtypes.add(new TypeDef("byte", "Byte", "int", "(byte) 0"));
        elmtypes.add(new TypeDef("short", "Short", "int", "(short) 0"));
        elmtypes.add(new TypeDef("int", "Integer", "long", "0"));
        elmtypes.add(new TypeDef("long", "Long", "long", "0L"));
        elmtypes.add(new TypeDef("float", "Float", "float", "0.0f"));
        elmtypes.add(new TypeDef("double", "Double", "double", "0.0"));
        elmtypes.add(new TypeDef("char", "Character", "int", "'\\0'"));
        elmtypes.add(new TypeDef("boolean", "Boolean")); // elm
        elmtypes.add(new TypeDef("Object")); // elm

        algtypes = new ArrayList<TypeDef>();
        algtypes.add(new TypeDef("byte", "Byte", "int", "(byte) 0"));
        algtypes.add(new TypeDef("short", "Short", "int", "(short) 0"));
        algtypes.add(new TypeDef("int", "Integer", "long", "0"));
        algtypes.add(new TypeDef("long", "Long", "long", "0L"));
        algtypes.add(new TypeDef("float", "Float", "float", "0.0f"));
        algtypes.add(new TypeDef("double", "Double", "double", "0.0"));
        algtypes.add(new TypeDef("char", "Character", "int", "'\\0'"));
        algtypes.add(new BigDecimal_t()); // alg
        algtypes.add(new BigInteger_t()); // alg

        inttypes = new ArrayList<TypeDef>();
        inttypes.add(new TypeDef("boolean", "Boolean", "int", "false"));
        inttypes.add(new TypeDef("byte", "Byte", "int", "(byte) 0"));
        inttypes.add(new TypeDef("short", "Short", "int", "(short) 0"));
        inttypes.add(new TypeDef("int", "Integer", "long", "0"));
        inttypes.add(new TypeDef("long", "Long", "long", "0L"));
        inttypes.add(new TypeDef("char", "Character", "int", "'\\0'"));
    }

    static final Pattern FNCALL = Pattern.compile(//
            "(\\w+)   \\(   ([^(]*?)   \\)", Pattern.COMMENTS);

    public static void main(String[] args)
            throws IOException {

        InputStream in = ArraysCG.class.getResourceAsStream("ArraysTemplate.java");
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        byte[] block = new byte[4096];
        int cb;
        while ((cb = in.read(block)) != -1) {
            buf.write(block, 0, cb);
        }
        in.close();

        String templ = new String(buf.toByteArray(), "utf-8");

        int begin = templ.indexOf("// SECTION");
        int end = templ.lastIndexOf("// END");
        templ = templ.substring(begin, end);

        while (templ != null) {
            int nextLine = templ.indexOf('\n');
            String mode = templ.substring(10, nextLine).trim();

            int nextSection = templ.indexOf("// SECTION", nextLine);

            String section;
            if (nextSection == -1) {
                section = templ.substring(nextLine).trim();
                templ = null;
            } else {
                section = templ.substring(nextLine, nextSection).trim();
                templ = templ.substring(nextSection);
            }

            List<TypeDef> types;
            boolean no_object = false;
            switch (mode) {
            case "_elm":
                no_object = true;
            case "elm":
                types = elmtypes;
                break;
            case "alg":
                types = algtypes;
                break;
            case "int":
                types = inttypes;
                break;
            default:
                throw new RuntimeException("Bad section mode: " + mode);
            }

            for (final TypeDef type : types) {
                if (no_object)
                    if (type.type_t.equals("Object"))
                        continue;

                String s = section;
                s = s.replace("type_t", type.type_t);
                s = s.replace("Type_t", type.Type_t);
                s = s.replace("sum_t", type.sum_t);
                s = s.replace("ZERO", type.ZERO);

                if ("alg".equals(mode)) {
                    TextPrepByParts prep = TextPrepByParts.match(FNCALL, new IPartProcessor() {
                        @Override
                        public String process(String part, Matcher matcher) {
                            String fn = matcher.group(1);
                            String args = matcher.group(2);
                            String argv[] = args.split(", ");
                            switch (fn) {
                            case "ADD":
                                part = type.ADD(argv[0], argv[1]);
                                break;
                            case "SUB":
                                part = type.SUB(argv[0], argv[1]);
                                break;
                            case "MULTIPLY":
                                part = type.MULTIPLY(argv[0], argv[1]);
                                break;
                            case "DIVIDE_N":
                                part = type.DIVIDE_N(argv[0], argv[1]);
                                break;
                            case "LESS_THAN":
                                part = type.LESS_THAN(argv[0], argv[1]);
                                break;
                            case "GREATER_THAN":
                                part = type.GREATER_THAN(argv[0], argv[1]);
                                break;
                            }
                            return part;
                        }
                    });
                    s = prep.process(s);
                }

                System.out.println("    " + s);
                System.out.println();
            }
        }

    }
}

class TypeDef {

    String type_t;;
    String Type_t;
    String sum_t;
    String ZERO = "0";

    public TypeDef(String type_t) {
        this(type_t, type_t);
    }

    public TypeDef(String type_t, String Type_t) {
        this.type_t = type_t;
        this.Type_t = Type_t;
        this.sum_t = type_t;
    }

    public TypeDef(String type_t, String Type_t, String sum_t, String ZERO) {
        this.type_t = type_t;
        this.Type_t = Type_t;
        this.sum_t = sum_t;
        this.ZERO = ZERO;
    }

    String ADD(String a, String b) {
        return a + " + " + b;
    }

    String SUB(String a, String b) {
        return a + " - " + b;
    }

    String MULTIPLY(String a, String b) {
        return a + " * " + b;
    }

    String DIVIDE_N(String a, String n) {
        return a + " / " + n;
    }

    String LESS_THAN(String a, String b) {
        return a + " < " + b;
    }

    String GREATER_THAN(String a, String b) {
        return a + " > " + b;
    }

}

class Number_t
        extends TypeDef {

    public Number_t(String type_t) {
        super(type_t);
    }

    public Number_t(String type_t, String Type_t) {
        super(type_t, Type_t);
    }

    public Number_t(String type_t, String Type_t, String sum_t, String ZERO) {
        super(type_t, Type_t, sum_t, ZERO);
    }

    @Override
    String ADD(String a, String b) {
        return a + ".add(" + b + ")";
    }

    @Override
    String SUB(String a, String b) {
        return a + ".subtract(" + b + ")";
    }

    @Override
    String MULTIPLY(String a, String b) {
        return a + ".multiply(" + b + ")";
    }

    @Override
    String LESS_THAN(String a, String b) {
        return a + ".compareTo(" + b + ") < 0";
    }

    @Override
    String GREATER_THAN(String a, String b) {
        return a + ".compareTo(" + b + ") > 0";
    }

}

class BigDecimal_t
        extends Number_t {

    public BigDecimal_t() {
        super("BigDecimal", "BigDecimal", "BigDecimal", "BigDecimal.ZERO");
    }

    @Override
    String DIVIDE_N(String a, String n) {
        return a + ".divide(BigDecimal.valueOf(" + n + "))";
    }

}

class BigInteger_t
        extends Number_t {

    public BigInteger_t() {
        super("BigInteger", "BigInteger", "BigInteger", "BigInteger.ZERO");
    }

    @Override
    String DIVIDE_N(String a, String n) {
        return a + ".divide(BigInteger.valueOf(" + n + "))";
    }

}