package net.bodz.bas.script;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;

import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonDumper;
import net.bodz.bas.fn.EvalException;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.process.MyProcessBuilder;
import net.bodz.bas.io.process.ProcessWrapper;
import net.bodz.bas.io.process.RunData;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.script.io.ResourceResolver;
import net.bodz.bas.script.js.PolyglotContext;
import net.bodz.bas.script.js.ValueDumper;

/**
 * Simple Javascript Runner
 */
@ProgramName("sjr")
public class SimpleJsRunner
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(SimpleJsRunner.class);

    /**
     * Use Java Scripting API (JSR-223).
     *
     * By default, GraalVM (Polyglot) is used.
     *
     * @option -j --scripting
     */
    boolean scripting = false;

    /**
     * Enable as many extensions as can.
     *
     * @option -X --extensions
     */
    boolean extensions = false;

    IPrintOut out = Stdio.cout;
    IPrintOut err = Stdio.cerr;

    public SimpleJsRunner() {
    }

    protected IScriptContext createScriptContext() {
        IScriptContext scriptContext;
        if (scripting) {
            ScriptEngine scriptEngine = ScriptEngineUtils.getEngine();
            scriptContext = new ScriptEngineContext(scriptEngine);
        } else {
            ResourceResolver resourceResolver = createResourceResolver();
            if (extensions)
                scriptContext = PolyglotContext.createContextVerbose(resourceResolver);
            else
                scriptContext = PolyglotContext.createContext(resourceResolver);
        }
        initScriptContext(scriptContext);
        return scriptContext;
    }

    protected ResourceResolver createResourceResolver() {
        ResourceResolver resourceResolver = new ResourceResolver();
        resourceResolver.searchWorkDir = true;
        resourceResolver.searchHomeDir = true;
        return resourceResolver;
    }

    protected void initScriptContext(IScriptContext scriptContext) {
        scriptContext.put("app", this);
    }

    String evalPretty(IScriptContext scriptContext, String code, String file)
            throws EvalException, IOException, FormatException {

        Object result = scriptContext.eval(code, file);

        JsonBuffer buf = new JsonBuffer();

        IJsonDumper dumper;
        dumper = new ValueDumper(buf);
        // dumper = new BeanJsonDumper(buf);
        dumper.dump(result);

        String json = buf.toString();
        json = format(json);
        return json;
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        IScriptContext scriptContext = createScriptContext();

        if (args.length > 0)
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                out.println("in> " + arg);

                String result;
                try {
                    result = evalPretty(scriptContext, arg, "arg-" + i);
                } catch (Throwable e) {
                    err.println("error: " + e.getMessage());
                    logger.error(e, "error: " + e.getMessage());
                    continue;
                }

                out.print("out> " + result);
                out.println();
            }
        else {
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(reader);
            String line;
            int lineNo = 0;
            while (true) {
                out.print(++lineNo + "> ");
                line = in.readLine();
                if (line == null)
                    break;
                if (line.equals("quit") || line.equals("q"))
                    break;

                String result;
                try {
                    result = evalPretty(scriptContext, line, "line-" + lineNo);
                } catch (Throwable e) {
                    err.println("error: " + e.getMessage());
                    logger.error(e, "error: " + e.getMessage());
//                    throw e;
                    continue;
                }
                out.print(result);
                out.println();
            }
        }
    }

    public static void main(String[] args)
            throws Exception {
        new SimpleJsRunner().execute(args);
    }

    static String format(String json)
            throws IOException {
        byte[] jsonBytes = json.getBytes("utf-8");
        InputStream jsonIn = new ByteArrayInputStream(jsonBytes);

        ProcessWrapper process = new MyProcessBuilder().command("jq", ".").start();
        process.supplyInput(jsonIn);

        RunData runData;
        try {
            runData = process.mergeOutputAndError().waitForRunData();

        } catch (InterruptedException e) {
            return null;
        }
        String pretty = runData.getOutputText();
        return pretty;
    }

}
