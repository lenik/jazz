package net.bodz.lily.gen.model.java;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.t.table.IColumnMetadata;
import net.bodz.bas.t.table.ITableMetadata;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.ISampleGenerator;
import net.bodz.lily.model.base.CoObject;
import net.bodz.lily.test.TestSamples;

public class EntitySamplesBuilder
        extends EntityClassBuilder {

    Random random = new Random();

    public EntitySamplesBuilder(String mainQName, String fragmentQName) {
        super(mainQName, fragmentQName);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        out.println("public class " + fragmentName);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s {\n", //
                        imports.simple(TestSamples.class));
                out.println();
                out.leave();
            }
            out.printf("public static %s build()\n", //
                    imports.simple(mainQName));
            out.println("        throws Exception {");
            out.enter();
            {
                out.printf("%s a = new %s();\n", //
                        imports.simple(mainQName), imports.simple(mainQName));

                for (IColumnMetadata column : table.getColumns()) {
                    String col_name = column.getName();
                    String colName = StringId.UL.toCamel(col_name);
                    String ColName = Strings.ucfirst(colName);

                    Class<?> type = column.getType();
                    // FK..
                    if (CoObject.class.isAssignableFrom(type))
                        continue;

                    String quoted = null;

                    if (type == String.class) {
                        int maxLen = column.getPrecision();
                        int wordMaxLen = 10;
                        String sample = EnglishTextGenerator.makeText(maxLen, wordMaxLen);
                        quoted = StringQuote.qqJavaString(sample.toString());
                    } else if (type == BigDecimal.class) {
                        int precision = column.getPrecision();
                        int scale = column.getScale();
                        int maxLen = precision;
                        if (scale != 0)
                            maxLen -= scale + 1;

                        int len = random.nextInt(maxLen) + 1;
                        StringBuilder sb = new StringBuilder(len);
                        for (int i = 0; i < len; i++) {
                            int digit;
                            do {
                                digit = random.nextInt(10);
                            } while (digit == 0 && i == 0);
                            sb.append('0' + digit);
                        }
                        imports.ref(BigDecimal.class);
                        quoted = "new BigDecimal(\"" + sb + "\")";
                    } else {
                        ISampleGenerator<?> generator = Typers.getTyper(type, ISampleGenerator.class);
                        // IToJavaCode toJavaCode= Typers.getTyper(type, IToJavaCode.class);
                        if (generator != null) {
                            Object sample = generator.newSample();
                            if (sample instanceof Date) {
                                String iso = Dates.ISO8601Z.format(sample);
                                String isoQuoted = StringQuote.qqJavaString(iso);
                                imports.ref(Dates.class);
                                String timeQuoted = "Dates.ISO8601Z.parse(" + isoQuoted + ").getTime()";
                                quoted = "new " + imports.simple(type) + "(" + timeQuoted + ")";
                            } else {
                                quoted = sample.toString();
                            }
                        }
                    }
                    if (quoted != null)
                        out.printf("a.set%s(%s);\n", ColName, quoted);
                }
                out.println("return a;");
                out.leave();
            }
            out.println("}");
            out.println();
            out.leave();
        }
        out.println("}");

    }

}
