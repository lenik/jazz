package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.data.struct.IOctetStreamForm;
import net.bodz.bas.data.struct.ListBin;
import net.bodz.bas.data.struct.ListBinUtils;
import net.bodz.bas.data.struct.ListLengthType;
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
            String name = "this." + field.getName();
            loadField(out, i++, new VarField(field, name));
        }

        out.leaveln("}");
    }

    void loadField(JavaSourceWriter out, int index, VarField field) {
        if (IOctetStreamForm.class.isAssignableFrom(field.type)) {
            out.printf("%s.readObject(in);\n", field.name);
            return;
        }

        if (field.type.isArray()) {
            loadArrayField(out, index, field);
            return;
        }

        int typeId = TypeKind.getTypeId(field.type);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            out.printf("%s = in.readByte();\n", field.name);
            return;

        case TypeId._short:
        case TypeId.SHORT:
            out.printf("%s = in.readWord();\n", field.name);
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            out.printf("%s = in.readDword();\n", field.name);
            return;

        case TypeId._long:
        case TypeId.LONG:
            out.printf("%s = in.readQword();\n", field.name);
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            out.printf("%s = in.readFloat();\n", field.name);
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            out.printf("%s = in.readDouble();\n", field.name);
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            out.printf("%s = in.readBool();\n", field.name);
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
            out.printf("%s = in.readUtf8Char();\n", field.name);
            return;

        case TypeId.STRING:
            break;
        }
        loadStringField(out, field);
    }

    void loadArrayField(JavaSourceWriter out, int index, VarField field) {
        Class<?> type1 = field.type.getComponentType();

        String fn = null;
        int typeId = TypeKind.getTypeId(type1);
        int itemSize = 0;
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            fn = "Bytes";
            itemSize = 1;
            break;

        case TypeId._short:
        case TypeId.SHORT:
            fn = "Words";
            itemSize = 2;
            break;

        case TypeId._int:
        case TypeId.INTEGER:
            fn = "Dwords";
            itemSize = 4;
            break;

        case TypeId._long:
        case TypeId.LONG:
            fn = "Qwords";
            itemSize = 8;
            break;

        case TypeId._float:
        case TypeId.FLOAT:
            fn = "Floats";
            itemSize = 4;
            break;

        case TypeId._double:
        case TypeId.DOUBLE:
            fn = "Doubles";
            itemSize = 8;
            break;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            fn = "Bools";
            itemSize = 1;
            break;

        case TypeId._char:
        case TypeId.CHARACTER:
            loadStringField(out, field);
            return;
        }

        ListLengthType lenType;
        int providedCount = 0;
        String nExpr = null;

        ListBin aListBin = field.getAnnotation(ListBin.class);
        if (aListBin != null) {
            lenType = ListBinUtils.toListLengthType(aListBin);
            providedCount = ListBinUtils.getProvidedCount(aListBin);
            nExpr = String.valueOf(providedCount);
        } else {
            if (field.isFinal()) {
                lenType = ListLengthType.providedItemCount;
                nExpr = field.name + ".length";
            } else {
                lenType = ListLengthType.terminatedByNull;
            }
        }

        boolean multiLine = lenType.hasCountField || fn == null;
        if (multiLine && index != 0)
            out.println();

        String countVar = null;
        if (lenType.hasCountField) {
            countVar = field.name + "Count";
            out.printf("int %s = in.read%s();\n", countVar, lenType.countFieldType());
            nExpr = countVar;
        }

        if (fn != null) {
            if (lenType.countByByte)
                nExpr = nExpr + " / " + itemSize;
            if (field.isFinal()) {
                out.printf("in.read%s(%s, 0, %s);\n", fn, field.name, nExpr);
            } else {
                out.printf("%s = in.read%s(%s);\n", field.name, fn, nExpr);
            }

        } else {
            if (field.isNotFinal())
                out.printf("%s = new %s[%s];\n", //
                        field.name, type1.getSimpleName(), nExpr);

            boolean itemMultiLine = type1.isPrimitive() || type1 == String.class;

            out.printf("for (int i = 0; i < %s; i++)", nExpr);
            if (itemMultiLine)
                out.print(" {");
            out.enterln("");

            VarField itemField = new VarField(type1, 0, field.name + "[i]");
            loadField(out, 0, itemField);

            out.leave();
            if (itemMultiLine)
                out.println("}");
        }
    }

    void loadStringField(JavaSourceWriter out, VarField field) {
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
            if (field.type == char[].class)
                expr.append("in.readChars");
            else
                expr.append("in.readString");
            expr.append("(");

            String typeConst = StringLengthType.class.getSimpleName() + "." + lengthType.name;

            if (lengthType.hasCountField || lengthType.hasTerminator) {
                expr.append(typeConst + ", " + 0);
            } else {
                int providedCount = 0;
                if (aStringBin != null)
                    providedCount = StringBinUtils.getProvidedCount(aStringBin);
                if (lengthType.countByChar) {
                    expr.append(providedCount);
                } else {
                    expr.append(typeConst + ", " + providedCount);
                }
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

        if (field.type != char[].class) {
            out.print(field.name);
            out.print(" = ");
            if (field.type != String.class) {
                String parseMethodName = "parse" + NameConventions.getMethodName(field.type);
                out.printf("%s(%s)", parseMethodName, expr);
            } else {
                out.print(expr);
            }
            out.print(";");
        } else {
            out.print(expr);
            out.print(";");
        }
        out.println();
    }

}
