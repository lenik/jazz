package net.bodz.lily.gen.model.java;

import java.util.Date;

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

    public EntitySamplesBuilder(String fqcn) {
        super(fqcn);
    }

    @Override
    protected void buildClassBody(ITableMetadata table) {
        String table_name = table.getName();
        String camelName = StringId.UL.toCamel(table_name);
        String CamelName = Strings.ucfirst(camelName);

        String samplesType = CamelName + "Samples";

        out.println("public class " + samplesType);
        out.enter();
        {
            out.enter();
            {
                out.printf("extends %s {\n", //
                        imports.simple(TestSamples.class));
                out.println();
                out.leave();
            }
            out.printf("public static %s build() {\n", //
                    imports.simple(fqcn));
            out.enter();
            {
                out.printf("%s a = new %s();\n", //
                        imports.simple(fqcn), imports.simple(fqcn));

                for (IColumnMetadata column : table.getColumns()) {
                    String col_name = column.getName();
                    String colName = StringId.UL.toCamel(col_name);
                    String ColName = Strings.ucfirst(colName);

                    Class<?> type = column.getType();
                    // FK..
                    if (CoObject.class.isAssignableFrom(type))
                        continue;

                    ISampleGenerator<?> generator = Typers.getTyper(type, ISampleGenerator.class);
                    // IToJavaCode toJavaCode= Typers.getTyper(type, IToJavaCode.class);
                    if (generator != null) {
                        Object sample = generator.newSample();
                        String quoted = null;
                        if (sample instanceof String) {
                            quoted = StringQuote.qqJavaString(sample.toString());
                        } else if (sample instanceof Date) {
                            String iso = Dates.ISO8601Z.format(sample);
                            quoted = imports.simple(Dates.class) + ".ISO8601Z.parse(" + StringQuote.qqJavaString(iso)
                                    + ")";
                        } else {
                            quoted = sample.toString();
                        }
                        out.println("a.set%s(%s);", ColName, quoted);
                    }
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
