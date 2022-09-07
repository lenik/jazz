package net.bodz.lily.tool.javagen;

import java.io.File;

import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.rst.RstFn;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * Convert codegen-config in rst format to specified format.
 */
@ProgramName("ccc")
public class CodegenConfigConv
        extends BasicCLI {

    /**
     * Convert to JSON format.
     *
     * @option -j
     */
    boolean json;

    /**
     * Convert to RST format.
     *
     * @option -r
     */
    boolean rst;

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (!(json || rst)) {
            rst = true;
        }

        for (String arg : args) {
            CodegenConfig cc = new CodegenConfig();
            File file = new File(arg);
            RstFn.loadFromRst(cc, file);

            if (json) {
                String json = JsonFn.toJson(cc, JsonFormOptions.PRETTY);
                System.out.println(json);
            }

            if (rst) {
                String rst = RstFn.toString(cc);
                System.out.println(rst);
            }
        }
    }

    public static void main(String[] args)
            throws Exception {
        new CodegenConfigConv().execute(args);
    }

}
