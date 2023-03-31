package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.data.struct.IOctetStreamForm;
import net.bodz.bas.data.struct.StringBin;
import net.bodz.bas.data.struct.StringBinUtils;
import net.bodz.bas.io.StringLengthType;

public class OctetStreamInImpl
        extends FieldsRelatedSourceBuilder {

    Charset defaultCharset = Charsets.UTF_8;

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void readObject(IDataIn in)");
        out.enterln("        throws IOException, ParseException {");

        int i = 0;
        for (Field field : fields) {
            loadField(out, i++, field);
        }

        out.leaveln("}");
    }

    void loadField(JavaSourceWriter out, int index, Field field) {
        Class<?> type = field.getType();
        String var = field.getName();
        if (IOctetStreamForm.class.isAssignableFrom(type)) {
            out.printf("%s.readObject(in);\n", var);
            return;
        }

        if (type.isArray()) {
            loadArrayField(out, index, field);
            return;
        }

        int typeId = TypeKind.getTypeId(type);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            out.printf("this.%s = in.readByte();\n", var);
            return;

        case TypeId._short:
        case TypeId.SHORT:
            out.printf("this.%s = in.readWord();\n", var);
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            out.printf("this.%s = in.readDword();\n", var);
            return;

        case TypeId._long:
        case TypeId.LONG:
            out.printf("this.%s = in.readQword();\n", var);
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            out.printf("this.%s = in.readFloat();\n", var);
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            out.printf("this.%s = in.readDouble();\n", var);
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            out.printf("this.%s = in.readBool();\n", var);
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
            out.printf("this.%s = in.readUtf8Char();\n", var);
            return;

        case TypeId.STRING:
            break;
        }
        loadStringField(out, field, type, var);
    }

    void loadArrayField(JavaSourceWriter out, int index, Field field) {
        Class<?> type1 = field.getType().getComponentType();
        String fieldName = field.getName();

        String fn = null;
        int typeId = TypeKind.getTypeId(type1);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            fn = "Bytes";
            break;

        case TypeId._short:
        case TypeId.SHORT:
            fn = "Words";
            break;

        case TypeId._int:
        case TypeId.INTEGER:
            fn = "Dwords";
            break;

        case TypeId._long:
        case TypeId.LONG:
            fn = "Qwords";
            break;

        case TypeId._float:
        case TypeId.FLOAT:
            fn = "Floats";
            break;

        case TypeId._double:
        case TypeId.DOUBLE:
            fn = "Doubles";
            break;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            fn = "Bools";
            break;

//        case TypeId._char:
//        case TypeId.CHARACTER:
//            fn = "Chars";
//            break;
        }

        if (index != 0)
            out.println();

        String countVar = fieldName + "Count";
        out.printf("int %s = in.readDword();\n", countVar);
        out.printf("this.%s = new %s[%s];\n", fieldName, type1.getSimpleName(), countVar);
        out.printf("for (int i = 0; i < %s; i++)\n", countVar);
        out.print("    ");
        loadStringField(out, field, type1, fieldName + "[i]");
    }

    void loadStringField(JavaSourceWriter out, AnnotatedElement field, Class<?> type, String var) {
        StringLengthType lengthType = StringBinUtils.defaultStringLengthType;
        Charset charset = null;
        StringBin aStringBin = field.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = StringBinUtils.toStringLengthType(aStringBin);
            if (!aStringBin.encoding().isEmpty())
                charset = Charset.forName(aStringBin.encoding());
        }

        StringBuilder expr = new StringBuilder();
        {
            if (lengthType.hasCountField) {
                expr.append("in.readString(");
                expr.append(StringLengthType.class.getSimpleName());
                expr.append(".");
                expr.append(lengthType.name);
                expr.append(", 0");
            } else {
                int providedCount = StringBinUtils.getProvidedCount(aStringBin);
                expr.append("in.readString(");
                if (lengthType.countByByte) {
                    expr.append(StringLengthType.class.getSimpleName());
                    expr.append(".");
                    expr.append(lengthType.name);
                }
                expr.append(", ");
                expr.append(providedCount);
            }
            if (charset != null) {
                String refName = Charsets.getDeclaredName(charset);
                expr.append(", ");
                if (refName == null)
                    expr.append('"' + charset.name() + '"');
                else
                    expr.append(refName);
            }
            expr.append(")");
        }

        out.print("this.");
        out.print(var);
        out.print(" = ");
        if (type != String.class) {
            String parseMethodName = "parse" + NameConventions.getMethodName(type);
            out.printf("%s(%s)", parseMethodName, expr);
        } else {
            out.print(expr);
        }
        out.println();
    }

}
