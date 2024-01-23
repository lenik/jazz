package net.bodz.lily.util;

import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.t.pojo.eg.Person;
import net.bodz.lily.criterion.CriterionJsonParser;
import net.bodz.lily.criterion.ICriterion;
import net.bodz.lily.criterion.ICriterionVisitor;
import net.bodz.lily.criterion.LispFormatter;
import net.bodz.lily.criterion.SQLFormatter;

/**
 * Re-format criterion in json.
 */
@ProgramName("criterion")
public class CriterionJson
        extends BasicCLI {

    Format format = Format.JSON;

    /**
     * Specify the context class.
     *
     * @option -c --context-class =FQCN
     */
    Class<?> context = Person.class;

    /**
     * Convert to SQL where clause.
     *
     * @option -w --where
     */
    void formatSqlWhere() {
        format = Format.SQL_WHERE;
    }

    /**
     * Convert to Lisp-style
     *
     * @option -s --simple
     */
    void formatSimple() {
        format = Format.LISP;
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        for (String file : args) {
            String json = ResFn.file(file).read().readString();
            JsonVariant in = JsonFn.parseAny(json);

            CriterionJsonParser jsonParser = CriterionJsonParser.builder().context(context).build();
            ICriterion criterion = jsonParser.parse(in);

            StringBuilder buf = new StringBuilder();
            ICriterionVisitor formatter;

            switch (format) {
            case LISP:
                formatter = new LispFormatter(buf);
                break;

            case SQL_WHERE:
                formatter = new SQLFormatter(buf);
                break;

            case JSON:
                String json2 = JsonFn.toJson(criterion);
                System.out.println(json2);
                continue;

            default:
                throw new IllegalArgumentException();
            }

            criterion.accept(formatter);
            String result = buf.toString();
            System.out.println(result);
        }
    }

    public static void main(String[] args)
            throws Exception {
        new CriterionJson().execute(args);
    }

    static enum Format {
        SQL_WHERE,
        JSON,
        LISP,
    }

}
