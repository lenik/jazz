package net.bodz.bas.shell.util;

import java.io.IOException;
import java.lang.reflect.Field;

import net.bodz.bas.c.reflect.query.FieldSelection;
import net.bodz.bas.c.reflect.query.ReflectQuery;
import net.bodz.bas.codegen.IJavaCodegen;
import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.shell.util.fieldsCg.*;

/**
 * Generate fields with various serialization forms.
 */
@ProgramName("mkfields")
public class FieldsFormsCG
        extends BasicCLI
        implements
            IJavaCodegen<Iterable<Field>> {

    /**
     * Specify the class to read.
     *
     * @option -c --class =FQCN
     */
    Class<?> clazz;

    /**
     * Select members declared on the class and specified depth number of superclasses.
     *
     * (default public members)
     *
     * @option -d
     */
    boolean declaredOnly;

    /**
     * The max depth of inheritance to find declared fields. (default 1)
     *
     * @option -D --max-depth =NUM
     */
    int maxDepth = 1;

    /**
     * Select (public) bean properties.
     *
     * @option -p --properties
     */
    boolean beanProperties;

    /**
     * Generate implementation for IBinaryDataForm
     *
     * @option -b --binary-data
     */
    boolean binaryDataForm;

    /**
     * Generate implementation for IJsonForm.
     *
     * @option -j --json
     */
    boolean jsonForm;

    /**
     * Generate implementation for IRstForm.
     *
     * @option -r --rst
     */
    boolean rstForm;

    /**
     * Generate implementation for IXmlForm.
     *
     * @option -x --xml
     */
    boolean xmlForm;

    FieldSelection fields;

    public FieldsFormsCG() {
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {

        if (clazz == null) {
            if (args.length == 0)
                throw new IllegalArgumentException("expect classname");
            String fqcn = args[0];
            clazz = Class.forName(fqcn);
        }

        if (declaredOnly) {
            fields = ReflectQuery//
                    .selectDeclaredFields(clazz)//
                    .maxDepth(maxDepth) //
                    .staticMode(false) //
                    .finalMode(false);
        } else {
            fields = ReflectQuery.selectFields(clazz) //
                    .staticMode(false) //
                    .finalMode(false);
        }
        // this=new

        String packageName = clazz.getPackage().getName();
        BCharOut buf = new BCharOut();
        ITreeOut out = buf.indented();

        out.enterln("class " + clazz.getSimpleName() + "Fields " + "{");
        out.println();
        JavaSourceWriter javaWriter = new JavaSourceWriter(packageName, out);
        generateJavaSource(javaWriter, fields);
        out.println();
        out.leaveln("}");

        out = Stdio.out.indented();
        javaWriter.im.dump(out);
        out.println();
        out.println(buf);
    }

    public static void main(String[] args)
            throws Exception {
        new FieldsFormsCG().execute(args);
    }

    @Override
    public void generateJavaSource(JavaSourceWriter out, Iterable<Field> model)
            throws IOException {
        KConsts constKeys = new KConsts();
        run(out, constKeys);
        if (constKeys.getCount() == 0)
            return;

        if (binaryDataForm) {
            run(out, new OctetStreamInImpl());
            run(out, new OctetStreamOutImpl());
        }

        if (jsonForm) {
            run(out, new JsonInImpl());
            run(out, new JsonOutImpl());
        }

        if (rstForm) {
            run(out, new RstInImpl());
            run(out, new RstOutImpl());
        }

        if (xmlForm) {
            run(out, new XmlInImpl());
            run(out, new XmlOutImpl());
        }
    }

    void run(JavaSourceWriter out, FieldsRelatedSourceBuilder builder)
            throws IOException {
        builder.setFields(fields);
        out.println();
        builder.build(out);
    }

}
