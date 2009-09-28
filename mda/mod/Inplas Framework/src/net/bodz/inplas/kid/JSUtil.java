package net.bodz.inplas.kid;

import java.util.HashSet;
import java.util.Set;

public class JSUtil {

    private static final String[] jsKeywords1 = new String[] { "abstract",
            "else", "instanceof", "switch", "boolean", "enum", "int",
            "synchronized", "break", "export", "interface", "this", "byte",
            "extends", "long", "throw", "case", "false", "native", "throws",
            "catch", "final", "new", "transient", "char", "finally", "null",
            "true", "class", "float", "package", "try", "const", "for",
            "private", "typeof", "continue", "function", "protected", "val",
            "debugger", "goto", "public", "var", "default", "if", "return",
            "void", "delete", "implements", "short", "volatile", "do",
            "import", "static", "while", "double", "in", "super", "with", };

    private static final String[] jsKeywords2 = new String[] { "As", "event",
            "Is", "uint", "Assert", "get", "Namespace", "ulong", "Decimal",
            "include", "Require", "use", "Ensure", "internal", "Sbyte",
            "ushort", "Exclude", "invariant", "Set", };

    private static Set<String>    jsKeywords;
    static {
        jsKeywords = new HashSet<String>();
        for (String keyword : jsKeywords1)
            jsKeywords.add(keyword);
        for (String keyword : jsKeywords2)
            jsKeywords.add(keyword);
    }

    public static String getJSName(String name) {
        if (jsKeywords.contains(name))
            return name + "_";
        return name;
    }

}
