package net.bodz.bas.shell.util.fieldsCg;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.string.Phrase;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.codegen.JavaSourceWriter;
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
            loadProperty(out, field.getName(), field);
        }

        out.leaveln("}");
    }

    void loadProperty(JavaSourceWriter out, String member, Field field) {
        Phrase nam = Phrase.fooBar(field.getName());
    }

    LoadExpr loadExpr(Field field) {
        Class<?> type = field.getType();
        int typeId = TypeKind.getTypeId(type);
        switch (typeId) {
        case TypeId._byte:
        case TypeId.BYTE:
            return LoadExpr.simple("in.readByte()");

        case TypeId._short:
        case TypeId.SHORT:
            return LoadExpr.simple("in.readShort()");

        case TypeId._int:
        case TypeId.INTEGER:
            return LoadExpr.simple("in.readInt()");

        case TypeId._long:
        case TypeId.LONG:
            return LoadExpr.simple("in.readLong()");

        case TypeId._float:
        case TypeId.FLOAT:
            return LoadExpr.simple("in.readFloat()");

        case TypeId._double:
        case TypeId.DOUBLE:
            return LoadExpr.simple("in.readDouble()");

        case TypeId._boolean:
        case TypeId.BOOLEAN:
            return LoadExpr.simple("in.readBool()");

        case TypeId._char:
        case TypeId.CHARACTER:
            return LoadExpr.simple("in.readUtf8Char()");

        case TypeId.STRING:
            break;
        }

        LengthType lengthType = defaultLengthType;
        Charset charset = defaultCharset;
        StringBin aStringBin = field.getAnnotation(StringBin.class);
        if (aStringBin != null) {
            lengthType = aStringBin.len();
            charset = Charset.forName(aStringBin.encoding());
        }
        String lengthTypeExpr = "LengthType." + lengthType.name();
        String charsetId = charset.name().replace('-', '_');
        String stringExpr = String.format("in.readString(%s, Charsets.%s)", //
                lengthTypeExpr, charsetId);
        if (typeId == TypeId.STRING)
            return LoadExpr.simple(stringExpr);

        String parseExpr = String.format("parse%s(%s)", type.getSimpleName(), stringExpr);
        return LoadExpr.simple(parseExpr);
    }

}
