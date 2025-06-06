package net.bodz.bas.make.codegen;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Naming {

    static String[] tvInputs = "UVWXYZ"//
            .chars().mapToObj(n -> String.valueOf((char) n))//
            .collect(Collectors.toList()).toArray(new String[0]);

    public static String typeVar(int count, int index) {
        if (count <= tvInputs.length)
            return tvInputs[index];
        else
            return "U" + (index + 1);
    }

    public static String inputParam(int count, int index) {
        String U = typeVar(count, index);
        String var = "input" + " " + (index + 1);
        return U + var;
    }

    public static String _typeVars(int count) {
        return count == 0 ? "" : ", " + typeVars(count);
    }

    public static String typeVars_(int count) {
        return typeVars(count) + (count == 0 ? "" : ", ");
    }

    public static String typeVars(int count) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i != 0)
                buf.append(", ");
            String U = typeVar(count, i);
            buf.append(U);
        }
        return buf.toString();
    }

    public static String _typeVars(int count, String... suffices) {
        return count == 0 ? "" : ", " + typeVars(count, suffices);
    }

    public static String typeVars(int count, String... suffices) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            String U = typeVar(count, i);
            for (String suffix : suffices) {
                String Us = U + suffix;
                if (buf.length() != 0)
                    buf.append(", ");
                buf.append(Us);
            }
        }
        return buf.toString();
    }

    @SafeVarargs
    public static String typeVars(int count, Function<String, String>... typeFns) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            String U = typeVar(count, i);
            for (Function<String, String> typeFn : typeFns) {
                String type = typeFn.apply(U);
                if (buf.length() != 0)
                    buf.append(", ");
                buf.append(type);
            }
        }
        return buf.toString();
    }

    public static String inputParams(int count) {
        return inputParams(count, "", "");
    }

    public static String inputParams(int count, String typeSuffix) {
        return inputParams(count, typeSuffix, "");
    }

    public static String inputParams(int count, String typeSuffix, String paramPrefix) {
        return inputParams(count, typeSuffix, paramPrefix, "");
    }

    public static String inputParams(int count, String typeSuffix, String paramPrefix, String varSuffix) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i != 0)
                buf.append(", ");
            String type = typeVar(count, i) + typeSuffix;
            String var = "input" + (i + 1) + varSuffix;
            buf.append(paramPrefix);
            buf.append(type).append(" ").append(var);
        }
        return buf.toString();
    }

    public static String inputParams(int count, Function<String, String> typeFn, String varSuffix) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i != 0)
                buf.append(", ");
            String U = typeVar(count, i);
            String type = typeFn.apply(U);
            String var = "input" + (i + 1) + varSuffix;
            buf.append(type).append(" ").append(var);
        }
        return buf.toString();
    }

    static int count(String s, String pattern) {
        int pos = 0;
        int count = 0;
        while ((pos = s.indexOf(pattern, pos)) != -1) {
            count++;
            pos += pattern.length();
        }
        return count;
    }

    public static String formatVars(String format, int count) {
        int placeholders = count(format, "%d");
        Object[] args = new Object[placeholders];
        StringBuilder buf = new StringBuilder();
        for (int index = 0; index < count; index++) {
            if (index != 0)
                buf.append(", ");
            Arrays.fill(args, index + 1);
            String var = String.format(format, args);
            buf.append(var);
        }
        return buf.toString();
    }

    public static String vars(String prefix, int count) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i != 0)
                buf.append(", ");
            String var = prefix + (i + 1);
            buf.append(var);
        }
        return buf.toString();
    }

    public static String vars(String prefix, int count, String... suffices) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            String var = prefix + (i + 1);
            for (String suffix : suffices) {
                String varStuff = var + suffix;
                if (buf.length() != 0)
                    buf.append(", ");
                buf.append(varStuff);
            }
        }
        return buf.toString();
    }

    public static String inputVars(int count) {
        return vars("input", count, "");
    }

    public static String inputVars(int count, String... suffices) {
        return vars("input", count, suffices);
    }

}
