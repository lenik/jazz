package net.bodz.bas.script;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;

import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fn.EvalException;
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
        ValueDumper dumper = new ValueDumper(buf);
        dumper.dumpBoxed(result);
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
                System.out.println("in> " + arg);

                String result;
                try {
                    result = evalPretty(scriptContext, arg, "arg-" + i);
                } catch (Throwable e) {
                    System.err.println("error: " + e.getMessage());
                    logger.error(e, "error: " + e.getMessage());
                    continue;
                }

                System.out.print("out> " + result);
                System.out.println();
            }
        else {
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(reader);
            String line;
            int lineNo = 0;
            while (true) {
                System.out.print(++lineNo + "> ");
                line = in.readLine();
                if (line == null)
                    break;

                String result;
                try {
                    result = evalPretty(scriptContext, line, "line-" + lineNo);
                } catch (Throwable e) {
                    System.err.println("error: " + e.getMessage());
                    logger.error(e, "error: " + e.getMessage());
//                    throw e;
                    continue;
                }
                System.out.print(result);
                System.out.println();
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

        Process jq = Processes.exec("jq", ".");
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream();
        try {
            Processes.iocap(jq, jsonIn, outbuf, outbuf);
        } catch (InterruptedException e) {
            return null;
        }
        String pretty = outbuf.toString("utf-8");
        return pretty;
    }

}
