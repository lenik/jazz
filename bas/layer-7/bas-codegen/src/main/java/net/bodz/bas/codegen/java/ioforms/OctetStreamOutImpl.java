package net.bodz.bas.codegen.java.ioforms;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.data.struct.IOctetStreamForm;
import net.bodz.bas.data.struct.StringBin;
import net.bodz.bas.data.struct.StringBinUtils;
import net.bodz.bas.io.StringLengthType;
import net.bodz.bas.codegen.java.JavaCodeWriter;
import net.bodz.bas.codegen.java.member.ContextFieldExprAsAMember;
import net.bodz.bas.codegen.java.member.IMember;

public class OctetStreamOutImpl
        extends SourceBuilderForMembers {

    StringLengthType defaultLengthType = StringLengthType.terminatedByNull;
    Charset defaultCharset = Charsets.UTF_8;

    @Override
    public void build(JavaCodeWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void writeObject(IDataOut out)");
        out.enterln("        throws IOException {");

        // int i = 0;
        for (IMember member : members) {
            saveMember(out, member);
        }

        out.leaveln("}");
    }

    void saveMember(JavaCodeWriter out, IMember member) {
        if (member.getType().isArray()) {
            saveArrayMember(out, member);
            return;
        }

        if (IOctetStreamForm.class.isAssignableFrom(member.getType())) {
            out.printLineWithJavaGet(member, "\\?.writeObject(out);", member.javaGet());
            return;
        }

        int typeId = TypeKind.getTypeId(member.getType());
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            out.printLineWithJavaGet(member, "out.write(\\?);");
            return;

        case TypeId._short:
        case TypeId.SHORT:
            out.printLineWithJavaGet(member, "out.writeWord(\\?);");
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            out.printLineWithJavaGet(member, "out.writeDword(\\?);");
            return;

        case TypeId._long:
        case TypeId.LONG:
            out.printLineWithJavaGet(member, "out.writeQword(\\?);");
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            out.printLineWithJavaGet(member, "out.writeFloat(\\?);");
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            out.printLineWithJavaGet(member, "out.writeDouble(\\?);");
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            out.printLineWithJavaGet(member, "out.writeBool(\\?);");
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
        case TypeId.STRING:
            break;
        }

        saveStringMember(out, member);
    }

    void saveArrayMember(JavaCodeWriter out, IMember member) {
        Class<?> type1 = member.getType().getComponentType();
        int typeId = TypeKind.getTypeId(type1);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            out.printLineWithJavaGet(member, "out.write(\\?);");
            return;

        case TypeId._short:
        case TypeId.SHORT:
            out.printLineWithJavaGet(member, "out.writeWords(\\?);");
            return;

        case TypeId._int:
        case TypeId.INTEGER:
            out.printLineWithJavaGet(member, "out.writeDwords(\\?);");
            return;

        case TypeId._long:
        case TypeId.LONG:
            out.printLineWithJavaGet(member, "out.writeQwords(\\?);");
            return;

        case TypeId._float:
        case TypeId.FLOAT:
            out.printLineWithJavaGet(member, "out.writeFloats(\\?);");
            return;

        case TypeId._double:
        case TypeId.DOUBLE:
            out.printLineWithJavaGet(member, "out.writeDoubles(\\?);");
            return;

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            out.printLineWithJavaGet(member, "out.writeBools(\\?);");
            return;

        case TypeId._char:
        case TypeId.CHARACTER:
        case TypeId.STRING:
            break;
        }

        StringLengthType lengthType = defaultLengthType;
        Charset charset = defaultCharset;
        StringBin aStringBin = member.getAnnotation(StringBin.class);
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
                out.printLineWithJavaGet(member, "out.writeUtf8Chars(\\?);");
                return;
            case 16:
                out.printLineWithJavaGet(member, "out.writeChars(\\?);");
                return;
            }
            String charsetId = charset.name().replace('-', '_');
            out.printLineWithJavaGet(member, "out.writeChars(\\?, Charsets." + charsetId + ");");
            return;
        }

        String javaGet = member.javaGet();
        if (javaGet != null) { // member is readable.
            out.printf("out.writeDword(%s.length);\n", javaGet);
            out.printf("for (%s a : %s) {\n", type1, javaGet);
            out.enter();
            IMember itemField = new ContextFieldExprAsAMember(member, "[i]", type1);
            saveStringMember(out, itemField);
            out.leave();
            out.println("}");
        }
    }

    void saveStringMember(JavaCodeWriter out, IMember member) {
        StringLengthType lengthType = StringBinUtils.defaultStringLengthType;

        Charset charset = null;
        StringBin aStringBin = member.getAnnotation(StringBin.class);
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

        int typeId = TypeKind.getTypeId(member.getType());
        switch (typeId) {
        case TypeId._char:
        case TypeId.CHARACTER:
            switch (unicode) {
            case 8:
                out.printLineWithJavaGet(member, "out.writeUtf8Char(%s);");
                return;
            case 16:
                out.printLineWithJavaGet(member, "out.writeChar(%s);");
                return;
            }
            out.printLineWithJavaGet(member, "out.writeChar(%s%s);", charsetParam);
            return;
        }

        String lengthTypeConst = StringLengthType.class.getSimpleName() + "." + lengthType.name;
        if (lengthType.hasCountField) {
            switch (unicode) {
            case 8:
                out.printLineWithJavaGet(member, "out.writeUtf8String(%s, \\?);", lengthTypeConst);
                break;
            case 16:
                out.printLineWithJavaGet(member, "out.writeString(%s, \\?);", lengthTypeConst);
                break;
            default:
                out.printLineWithJavaGet(member, "out.writeString(%s, \\?%s);", lengthTypeConst, charsetParam);
            }
            return;
        } else if (lengthType.hasTerminator) {
            switch (unicode) {
            case 8:
                out.printLineWithJavaGet(member, "out.writeUtf8String(\\?);");
                break;
            case 16:
                out.printLineWithJavaGet(member, "out.writeString(\\?);");
                break;
            default:
                out.printLineWithJavaGet(member, "out.writeString(\\?%s);", charsetParam);
            }
            out.printLineWithJavaGet(member, "out.writeChar(%s);", lengthType.terminatorJavaLiteral);
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
                    out.printLineWithJavaGet(member, "out.writeUtf8StringOfLength(%s, \\?);", nexpr);
                    break;
                case 16:
                    out.printLineWithJavaGet(member, "out.writeStringOfLength(%s, \\?);", nexpr);
                    break;
                default:
                    out.printLineWithJavaGet(member, "out.writeStringOfLength(%s, \\?%s);", nexpr, charsetParam);
                }
                return;
            } else {
                switch (unicode) {
                case 8:
                    out.printLineWithJavaGet(member, "out.writeUtf8StringOfSize(%s, \\?);", nexpr);
                    break;
                case 16:
                    out.printLineWithJavaGet(member, "out.writeStringOfSize(%s, \\?);", nexpr);
                    break;
                default:
                    out.printLineWithJavaGet(member, "out.writeStringOfSize(%s, \\?%s);", nexpr, charsetParam);
                }
                return;
            }
        }
    }

}
