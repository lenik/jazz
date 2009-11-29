package net.bodz.bas.semantictype;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import net.bodz.bas.aspect.typeinfo.InstanceStore;
import net.bodz.bas.semantictype.ISemanticType.Parameter;

public class SemanticTypeUtil {

    public static String dumpTypeStructure(ISemanticType<?> type) {
        StringWriter buffer = new StringWriter();
        try {
            dumpTypeStructure(type, buffer, "");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return buffer.toString();
    }

    static void dumpTypeStructure(ISemanticType<?> type, Writer out, String indent)
            throws IOException {
        out.write(indent);

        String name = type.getName();
        out.write(name);

        String description = type.getDescription();
        if (description != null)
            out.write(" - " + description);

        out.write("\n");
        indent += "    ";

        Class<?> instanceClass = type.getInstanceClass();
        out.write(indent + "Class: " + instanceClass + "\n");

        String syntax = type.getSyntax();
        if (syntax != null)
            out.write(indent + "Syntax: " + syntax + "\n");

        int count = type.getParameterCount();
        for (int i = 0; i < count; i++) {
            Parameter<?> param = type.getParameter(i);
            out.write(indent + "Param " + (i + 1) + ":" + param.getName() + " = " + param.getValue());
            ISemanticType<?> paramType = param.getType();
            if (paramType != null)
                dumpTypeStructure(paramType, out, indent + "  | ");
        }

        InstanceStore<?> store = type.getInstanceStore();
        count = store.getInstanceCount();
        for (int i = 0; i < count; i++) {
            Object instance = store.getInstance(i);
            out.write(indent + "Instance " + (i + 1) + ": " + instance);
        }
    }

}
