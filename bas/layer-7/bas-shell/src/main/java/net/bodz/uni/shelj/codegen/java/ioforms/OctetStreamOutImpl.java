package net.bodz.uni.shelj.codegen.java.ioforms;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.data.struct.IOctetStreamForm;
import net.bodz.bas.data.struct.StringBin;
import net.bodz.bas.data.struct.StringBinUtils;
import net.bodz.bas.io.StringLengthType;
import net.bodz.uni.shelj.codegen.java.member.ContextFieldExprAsAMember;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public class OctetStreamOutImpl
        extends SourceBuilderForMembers {

    StringLengthType defaultLengthType = StringLengthType.terminatedByNull;
    Charset defaultCharset = Charsets.UTF_8;

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void writeObject(IDataOut out)");
        out.enterln("        throws IOException {");

        int i = 0;
        for (IMember member : members) {
            saveField(out, member, i++);
        }

        out.leaveln("}");
    }

    void saveField(JavaSourceWriter out, IMember member, int index) {
        if (member.getType().isArray()) {
            saveArrayField(out, member, index);
            return;
        }

        if (IOctetStreamForm.class.isAssignableFrom(member.getType())) {
            out.printf("%s.writeObject(out);\n", member.javaGet());
            return;
        }

        int typeId = TypeKind.getTypeId(member.getType());
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            member.printStatementLineWithJavaGet(out, "out.write(\\?);");
            return;

        case TypeId._short:
        case TypeId.SHORT:
            member.printStatementLineWithJavaGet(out, "out.writeWord(\\?);");
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            member.printStatementLineWithJavaGet(out, "out.writeDword(\\?);");
            return;

        case TypeId._long:
        case TypeId.LONG:
            member.printStatementLineWithJavaGet(out, "out.writeQword(\\?);");
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            member.printStatementLineWithJavaGet(out, "out.writeFloat(\\?);");
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            member.printStatementLineWithJavaGet(out, "out.writeDouble(\\?);");
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            member.printStatementLineWithJavaGet(out, "out.writeBool(\\?);");
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
        case TypeId.STRING:
            break;
        }

        saveStringField(out, member, index);
    }

    void saveArrayField(JavaSourceWriter out, IMember field, int index) {
        Class<?> type1 = field.getType().getComponentType();
        int typeId = TypeKind.getTypeId(type1);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            out.printf("out.write(%s);\n", field.javaGet());
            return;

        case TypeId._short:
        case TypeId.SHORT:
            out.printf("out.writeWords(%s);\n", field.javaGet());
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            out.printf("out.writeDwords(%s);\n", field.javaGet());
            return;

        case TypeId._long:
        case TypeId.LONG:
            out.printf("out.writeQwords(%s);\n", field.javaGet());
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            out.printf("out.writeFloats(%s);\n", field.javaGet());
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            out.printf("out.writeDoubles(%s);\n", field.javaGet());
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            out.printf("out.writeBools(%s);\n", field.javaGet());
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
        case TypeId.STRING:
            break;
        }

        StringLengthType lengthType = defaultLengthType;
        Charset charset = defaultCharset;
        StringBin aStringBin = field.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = StringBinUtils.toStringLengthType(aStringBin);
            if (!aStringBin.encoding().isEmpty())
                charset = Charset.forName(aStringBin.encoding());
        }
        int unicode = 0;
        switch (charset.name()) {
        case "UTF-8":
            unicode = 8;
            charset = Charsets.UTF_8;
            break;
        case "UTF-16":
            unicode = 16;
            break;
        }

        switch (typeId) {
        case TypeId._char:
        case TypeId.CHARACTER:
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8Chars(%s);\n", field.javaGet());
                return;
            case 16:
                out.printf("out.writeChars(%s);\n", field.javaGet());
                return;
            }
            String charsetId = charset.name().replace('-', '_');
            out.printf("out.writeChars(%s, Charsets." + charsetId + ");\n", field.javaGet());
            return;
        }

        out.printf("out.writeDword(%s.length);");
        out.printf("for (" + type1 + " a: " + field.javaGet() + ") {");
        out.enter();
        IMember itemField = new ContextFieldExprAsAMember(field, "[i]", type1);
        saveStringField(out, itemField, 0);
        out.leave();
        out.println("}");
    }

    void saveStringField(JavaSourceWriter out, IMember field, int index) {
        StringLengthType lengthType = StringBinUtils.defaultStringLengthType;

        Charset charset = null;
        StringBin aStringBin = field.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = StringBinUtils.toStringLengthType(aStringBin);
            String encoding = aStringBin.encoding();
            if (!encoding.isEmpty())
                charset = Charset.forName(encoding);
        }

        int unicode = 0;
        if (charset != null) {
            String name = charset.name();
            if (name.equals("UTF-8"))
                unicode = 8;
            else if (name.startsWith("UTF-16"))
                unicode = 16;
            else if (name.startsWith("UTF-32"))
                unicode = 32;
        }

        String charsetId = charset == null ? null : charset.name().replace('-', '_');
        String charsetParam = "";
        if (charsetId != null)
            charsetParam = ", Charsets." + charsetId;

        int typeId = TypeKind.getTypeId(field.getType());
        switch (typeId) {
        case TypeId._char:
        case TypeId.CHARACTER:
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8Char(%s);\n", field.javaGet());
                return;
            case 16:
                out.printf("out.writeChar(%s);\n", field.javaGet());
                return;
            }
            out.printf("out.writeChar(%s%s);\n", field.javaGet(), charsetParam);
            return;
        }

        String lengthTypeConst = StringLengthType.class.getSimpleName() + "." + lengthType.name;
        if (lengthType.hasCountField) {
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8String(%s, %s);\n", lengthTypeConst, field.javaGet());
                break;
            case 16:
                out.printf("out.writeString(%s, %s);\n", lengthTypeConst, field.javaGet());
                break;
            default:
                out.printf("out.writeString(%s, %s%s);\n", lengthTypeConst, field.javaGet(), charsetParam);
            }
            return;
        } else if (lengthType.hasTerminator) {
            switch (unicode) {
            case 8:
                out.printf("out.writeUtf8String(%s);\n", field.javaGet());
                break;
            case 16:
                out.printf("out.writeString(%s);\n", field.javaGet());
                break;
            default:
                out.printf("out.writeString(%s%s);\n", field.javaGet(), charsetParam);
            }
            out.printf("out.writeChar(%s);\n", lengthType.terminatorJavaLiteral);
            return;
        } else {
            int providedCount;
            String nexpr;
            if (aStringBin != null) {
                providedCount = StringBinUtils.getProvidedCount(aStringBin);
                nexpr = "" + providedCount;
            } else {
                providedCount = 0; // from source...
                nexpr = "" + providedCount;
            }

            if (lengthType.countByChar) {
                switch (unicode) {
                case 8:
                    out.printf("out.writeUtf8StringOfLength(%s, %s);\n", nexpr, field.javaGet());
                    break;
                case 16:
                    out.printf("out.writeStringOfLength(%s, %s);\n", nexpr, field.javaGet());
                    break;
                default:
                    out.printf("out.writeStringOfLength(%s, %s%s);\n", nexpr, field.javaGet(), charsetParam);
                }
                return;
            } else {
                switch (unicode) {
                case 8:
                    out.printf("out.writeUtf8StringOfSize(%s, %s);\n", nexpr, field.javaGet());
                    break;
                case 16:
                    out.printf("out.writeStringOfSize(%s, %s);\n", nexpr, field.javaGet());
                    break;
                default:
                    out.printf("out.writeStringOfSize(%s, %s%s);\n", nexpr, field.javaGet(), charsetParam);
                }
                return;
            }
        }
    }

}
