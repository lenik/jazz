package net.bodz.lily.tool.javagen;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

import net.bodz.bas.c.java.lang.OptionNames;
import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.codegen.EnglishTextGenerator;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.ISampleGenerator;
import net.bodz.lily.model.base.CoObject;
import net.bodz.lily.test.TestSamples;

public class FooSamples__java
        extends JavaGen__java {

    Random random;
    EnglishTextGenerator enGen;

    int maxStringLen = 1000;

    public FooSamples__java(JavaGenProject project) {
        super(project, project.FooSamples);
        this.random = project.random;
        this.enGen = new EnglishTextGenerator(project.random);
    }

    @Override
    protected void buildClassBody(JavaSourceWriter out, ITableViewMetadata table) {
        out.println("public class " + project.FooSamples.name);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s {\n", //
                        out.im.name(TestSamples.class));
                out.println();
                out.leave();
            }
            out.printf("public static %s build()\n", //
                    out.im.name(project.Foo));
            out.println("        throws Exception {");
            out.enter();
            {
                out.printf("%s a = new %s();\n", //
                        out.im.name(project.Foo), out.im.name(project.Foo));

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
                        if (maxLen <= 0)
                            throw new IllegalArgumentException("invalid varchar length: " + maxLen);
                        if (maxLen > maxStringLen)
                            maxLen = maxStringLen;
                        int wordMaxLen = 10;
                        String sample = enGen.makeText(maxLen, wordMaxLen);
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
                        out.im.name(BigDecimal.class);
                        quoted = "new BigDecimal(\"" + sb + "\")";
                    } else if (type == BigInteger.class) {
                        int maxLen = column.getPrecision();
                        int len = random.nextInt(maxLen) + 1;
                        StringBuilder sb = new StringBuilder(len);
                        for (int i = 0; i < len; i++) {
                            int digit;
                            do {
                                digit = random.nextInt(10);
                            } while (digit == 0 && i == 0);
                            sb.append('0' + digit);
                        }
                        out.im.name(BigInteger.class);
                        quoted = "new BigInteger(\"" + sb + "\")";
                    } else {
                        ISampleGenerator<?> generator = Typers.getTyper(type, ISampleGenerator.class);
                        // IToJavaCode toJavaCode= Typers.getTyper(type, IToJavaCode.class);
                        if (generator != null) {
                            Options options = new Options();
                            options.addOption(OptionNames.signed, false);
                            options.addOption(Random.class, random);

                            Object sample = generator.newSample(options);

                            if (sample instanceof Date) {
                                String iso = Dates.ISO8601Z.format(sample);
                                String isoQuoted = StringQuote.qqJavaString(iso);
                                out.im.name(Dates.class);
                                String timeQuoted = "Dates.ISO8601Z.parse(" + isoQuoted + ").getTime()";
                                quoted = "new " + out.im.name(type) + "(" + timeQuoted + ")";

                            } else {
                                quoted = sample.toString();
                                switch (TypeKind.getTypeId(type)) {
                                case TypeId._byte:
                                case TypeId.BYTE:
                                    quoted = "(byte)" + quoted;
                                    break;

                                case TypeId._short:
                                case TypeId.SHORT:
                                    quoted = "(short)" + quoted;
                                    break;

                                case TypeId._long:
                                case TypeId.LONG:
                                    quoted = quoted + "L";
                                    break;
                                }
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
