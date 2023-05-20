package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;
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
import net.bodz.uni.shelj.codegen.java.JavaCodeWriter;
import net.bodz.uni.shelj.codegen.java.member.ContextFieldExprAsAMember;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public class OctetStreamInImpl
        extends SourceBuilderForMembers {

    Charset defaultCharset = Charsets.UTF_8;

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void readObject(IDataIn in)");
        out.enterln("        throws IOException, ParseException {");

        int i = 0;
        for (IMember member : members) {
            loadField(out, i++, member);
        }

        out.leaveln("}");
    }

    void loadField(JavaCodeWriter out, int index, IMember member) {
        Class<?> type = member.getType();
        if (IOctetStreamForm.class.isAssignableFrom(type)) {
            out.printLineWithJavaGet(member, "\\?.readObject(in);");
            return;
        }

        if (type.isArray()) {
            loadArrayField(out, index, member);
            return;
        }

        int typeId = TypeKind.getTypeId(type);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            out.printLineForJavaSet(member, "in.readByte()");
            return;

        case TypeId._short:
        case TypeId.SHORT:
            out.printLineForJavaSet(member, "in.readWord()");
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            out.printLineForJavaSet(member, "in.readDword()");
            return;

        case TypeId._long:
        case TypeId.LONG:
            out.printLineForJavaSet(member, "in.readQword()");
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            out.printLineForJavaSet(member, "in.readFloat()");
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            out.printLineForJavaSet(member, "in.readDouble()");
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            out.printLineForJavaSet(member, "in.readBool()");
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
            out.printLineForJavaSet(member, "in.readUtf8Char()");
            return;

        case TypeId.STRING:
            break;
        }
        loadStringField(out, member);
    }

    void loadArrayField(JavaCodeWriter out, int index, IMember member) {
        Class<?> type1 = member.getType().getComponentType();

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
            loadStringField(out, member);
            return;
        }

        ListLengthType lenType;
        int providedCount = 0;
        String nExpr = null;

        ListBin aListBin = member.getAnnotation(ListBin.class);
        if (aListBin != null) {
            lenType = ListBinUtils.toListLengthType(aListBin);
            providedCount = ListBinUtils.getProvidedCount(aListBin);
            nExpr = String.valueOf(providedCount);
        } else {
            if (member.isFinal()) {
                lenType = ListLengthType.providedItemCount;
                nExpr = member.javaGet() + ".length";
            } else {
                lenType = ListLengthType.terminatedByNull;
            }
        }

        boolean multiLine = lenType.hasCountField || fn == null;
        if (multiLine && index != 0)
            out.println();

        String countVar = null;
        if (lenType.hasCountField) {
            countVar = member.getName() + "Count";
            out.printf("int %s = in.read%s();\n", countVar, lenType.countFieldType());
            nExpr = countVar;
        }

        if (fn != null) {
            if (lenType.countByByte)
                nExpr = nExpr + " / " + itemSize;
            if (member.isFinal()) {
                out.printLineWithJavaGet(member, //
                        "in.read%s(\\?, 0, %s);", fn, nExpr);
            } else {
                out.printLineForJavaSet(member, //
                        "in.read%s(%s);", fn, nExpr);
            }

        } else {
            if (member.isNotFinal())
                out.printLineForJavaSet(member, "new %s[%s]", //
                        type1.getSimpleName(), nExpr);

            boolean itemMultiLine = type1.isPrimitive() || type1 == String.class;

            out.printf("for (int i = 0; i < %s; i++)", nExpr);
            if (itemMultiLine)
                out.print(" {");
            out.enterln("");

            IMember child = new ContextFieldExprAsAMember(member, "[i]", type1);
            loadField(out, 0, child);

            out.leave();
            if (itemMultiLine)
                out.println("}");
        }
    }

    void loadStringField(JavaSourceWriter out, IMember member) {
        Class<?> type = member.getType();
        StringLengthType lengthType = StringBinUtils.defaultStringLengthType;
        Charset charset = null;
        StringBin aStringBin = member.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = StringBinUtils.toStringLengthType(aStringBin);
            if (!aStringBin.encoding().isEmpty())
                charset = Charset.forName(aStringBin.encoding());
        }

        StringBuilder expr = new StringBuilder();
        {
            if (type == char[].class)
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

        if (type != char[].class) {
            out.print(member.getName());
            out.print(" = ");
            if (type != String.class) {
                String parseMethodName = "parse" + NameConventions.getMethodName(type);
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
