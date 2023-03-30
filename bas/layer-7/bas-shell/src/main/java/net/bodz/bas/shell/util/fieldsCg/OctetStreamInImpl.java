package net.bodz.bas.shell.util.fieldsCg;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.data.struct.IOctetStreamForm;
import net.bodz.bas.data.struct.StringBin;
import net.bodz.bas.io.LengthType;

public class OctetStreamInImpl
        extends FieldsRelatedSourceBuilder {

    LengthType defaultLengthType = LengthType.terminatedByNul;
    Charset defaultCharset = Charsets.UTF8;

    @Override
    public void build(JavaSourceWriter out)
            throws IOException {
        out.println("@Override");
        out.println("public void readObject(IDataIn in)");
        out.enterln("        throws IOException, ParseException {");

        for (Field field : fields) {
            loadExpr(out, field, field.getName());
        }

        out.leaveln("}");
    }

    Object loadExpr(JavaSourceWriter out, Field field, String var) {
        Class<?> type = field.getType();
        if (IOctetStreamForm.class.isAssignableFrom(type))
            return printf(out, "%s.readObject(in);\n", var);

        if (type.isArray())
            return loadArrayExpr(out, field, var);
        int typeId = TypeKind.getTypeId(type);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            return printf(out, "%s = in.readByte();\n", var);

        case TypeId._short:
        case TypeId.SHORT:
            return printf(out, "%s = in.readWord();\n", var);

        case TypeId._int:
        case TypeId.INTEGER:
            return printf(out, "%s = in.readDword();\n", var);

        case TypeId._long:
        case TypeId.LONG:
            return printf(out, "%s = in.readQword();\n", var);

        case TypeId._float:
        case TypeId.FLOAT:
            return printf(out, "%s = in.readFloat();\n", var);

        case TypeId._double:
        case TypeId.DOUBLE:
            return printf(out, "%s = in.readDouble();\n", var);

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            return printf(out, "%s = in.readBool();\n", var);

        case TypeId._char:
        case TypeId.CHARACTER:
            return printf(out, "%s = in.readUtf8Char();\n", var);

        case TypeId.STRING:
            break;
        }

        LengthType lengthType = defaultLengthType;
        int length = -1;
        Charset charset = defaultCharset;
        StringBin aStringBin = field.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = aStringBin.lenType();
            length = aStringBin.len();
            charset = Charset.forName(aStringBin.encoding());
        }
        String lengthTypeExpr = "LengthType." + lengthType.name();
        String charsetId = charset.name().replace('-', '_');

        String stringExpr;
        if (lengthType.fixedSize)
            stringExpr = String.format("in.readChars(%d, Charsets.%s)", //
                    length, charsetId);
        else
            stringExpr = String.format("in.readString(%s, Charsets.%s)", //
                    lengthTypeExpr, charsetId);
        if (typeId == TypeId.STRING)
            return LoadExpr.simple(stringExpr);

        String parseExpr = String.format("parse%s(%s)", type.getSimpleName(), stringExpr);
        return LoadExpr.simple(parseExpr);
    }

    Object loadArrayExpr(JavaSourceWriter out, Field field, String var) {
        Class<?> type = field.getType().getComponentType();
        int typeId = TypeKind.getTypeId(type);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            return printf(out, "%s = in.readBytes();\n", var);

        case TypeId._short:
        case TypeId.SHORT:
            return printf(out, "%s = in.readWords();\n", var);

        case TypeId._int:
        case TypeId.INTEGER:
            return printf(out, "%s = in.readDwords();\n", var);

        case TypeId._long:
        case TypeId.LONG:
            return printf(out, "%s = in.readQwords();\n", var);

        case TypeId._float:
        case TypeId.FLOAT:
            return printf(out, "%s = in.readFloats();\n", var);

        case TypeId._double:
        case TypeId.DOUBLE:
            return printf(out, "%s = in.readDoubles();\n", var);

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            return printf(out, "%s = in.readBools();\n", var);

        case TypeId._char:
        case TypeId.CHARACTER:
            return printf(out, "%s = in.readUtf8Chars();\n", var);

        case TypeId.STRING:
            break;
        }

        LengthType lengthType = defaultLengthType;
        int length = -1;
        Charset charset = defaultCharset;
        StringBin aStringBin = field.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = aStringBin.lenType();
            length = aStringBin.len();
            charset = Charset.forName(aStringBin.encoding());
        }
        String lengthTypeExpr = "LengthType." + lengthType.name();
        String charsetId = charset.name().replace('-', '_');

        String stringExpr;
        if (lengthType.fixedSize)
            stringExpr = String.format("in.readChars(%d, Charsets.%s)", //
                    length, charsetId);
        else
            stringExpr = String.format("in.readString(%s, Charsets.%s)", //
                    lengthTypeExpr, charsetId);
        if (typeId == TypeId.STRING)
            return LoadExpr.simple(stringExpr);

        String parseExpr = String.format("parse%s(%s)", type.getSimpleName(), stringExpr);
        return LoadExpr.simple(parseExpr);
    }

}
