package net.bodz.bas.make.codegen;

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
            String Us = typeVar(count, i) + typeSuffix;
            String var = "input" + (i + 1) + varSuffix;
            buf.append(paramPrefix);
            buf.append(Us).append(" ").append(var);
        }
        return buf.toString();
    }

    public static String inputVars(int count) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i != 0)
                buf.append(", ");
            String var = "input" + (i + 1);
            buf.append(var);
        }
        return buf.toString();
    }

    public static String inputVars(int count, String... suffices) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            String var = "input" + (i + 1);
            for (String suffix : suffices) {
                String varStuff = var + suffix;
                if (buf.length() != 0)
                    buf.append(", ");
                buf.append(varStuff);
            }
        }
        return buf.toString();
    }

}
