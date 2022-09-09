package net.bodz.lily.tool.javagen;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

import net.bodz.bas.c.java.lang.OptionNames;
import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.codegen.EnglishTextGenerator;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;
import net.bodz.bas.t.catalog.Phrase;
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
                    Phrase name = column.nam();
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
                        int intLen = precision;
                        if (scale != 0)
                            intLen -= scale + 1;

                        StringBuilder sb = new StringBuilder(precision);
                        randomDigits(sb, random.nextInt(intLen) + 1, true);

                        if (scale != 0 && random.nextBoolean()) {
                            sb.append('.');
                            randomDigits(sb, scale, false);
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
                        out.printf("a.set%s(%s);\n", name.FooBar, quoted);
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

    void randomDigits(StringBuilder sb, int len, boolean noZeroStart) {
        for (int i = 0; i < len; i++) {
            int digit;
            while (true) {
                digit = random.nextInt(10);
                if (i == 0)
                    if (noZeroStart && digit == 0)
                        continue;
                break;
            }
            char ch = (char) ('0' + digit);
            sb.append(ch);
        }
    }

}
