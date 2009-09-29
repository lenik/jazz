package net.bodz.mda.tmpl.xtx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.a.Doc;
import net.bodz.bas.a.ProgramName;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.cli.BasicCLI;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.io.CharOuts.WriterCharOut;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.sg.InvalidStateException;
import net.bodz.bas.types.util.Strings;
import net.bodz.mda.tmpl.xtx.langs.Perl;
import net.bodz.mda.tmpl.xtx.langs.Python;

@Doc("XTX Interpreter")
@ProgramName("xtx")
@RcsKeywords(id = "$Id$")
@Version( { 0, 1 })
public class XtxMain extends BasicCLI {

    public static final int CODE_GATE = '\u00AC';                // ¬
    public static final int ESCAPE    = '\\';

    @Option(alias = "e", vnam = "CHARSET", doc = "encoding of template file")
    Charset                 encoding  = Charset.forName("utf-8");

    @Option(alias = "c", vnam = "DIR", doc = "where to put compiled templates")
    File                    compileDirectory;

    @Option(alias = "t", doc = "only compile the template")
    boolean                 compileOnly;

    @Option(alias = "o", vnam = "DIR", doc = "where to put result files, or directory")
    File                    output;

    @Option(alias = "B", doc = "don't compare with last modified time")
    boolean                 alwaysMake;

    @Option(alias = "s", doc = "send output to stdout")
    boolean                 stdout;

    @Option(vnam = "FILE", fileIndex = 0, required = true)
    File                    template;

    String                  templateNameWithoutXtx;
    File                    scriptFile;
    File                    resultFile;

    @Override
    protected void _boot() throws Exception {
        if (template == null)
            throw new IllegalUsageException("template isn't specified");

        String name = template.getName();
        if (name.endsWith(".xtx"))
            templateNameWithoutXtx = name.substring(0, name.length() - 4);
        else if (name.endsWith("t"))
            templateNameWithoutXtx = name.substring(0, name.length() - 1);
        else
            throw new IllegalUsageError("illegal xtx-template extension");

        if (compileDirectory == null)
            if (output != null && output.isDirectory())
                compileDirectory = output;
            else
                compileDirectory = template.getParentFile();
        scriptFile = new File(compileDirectory, templateNameWithoutXtx);
        L.debug("compile-directory: ", compileDirectory);

        if (output == null)
            output = compileDirectory;
        if (!output.isFile()) {
            String resultName = Files.getName(templateNameWithoutXtx);
            output = new File(output, resultName);
        }
        L.debug("result-output: ", output);
    }

    @Override
    protected void doMain(String[] args) throws Exception {
        L.detail("search matching xtx-language for ", templateNameWithoutXtx);
        XtxLang matchingLang = null;
        for (XtxLang lang : languages) {
            if (lang.matches(templateNameWithoutXtx)) {
                L.detail("found matching xtx-language: ", lang);
                matchingLang = lang;
                break;
            }
        }
        if (matchingLang == null)
            throw new UnsupportedOperationException("No matching host lang for name: "
                    + templateNameWithoutXtx);

        if (alwaysMake || !scriptFile.exists() || //
                template.lastModified() > scriptFile.lastModified()) {
            L.detail("preprocess ", template, " -> ", scriptFile);
            Reader templateReader = Files.getReader(template, encoding);
            CharOut scriptOut;
            if (stdout && compileOnly)
                scriptOut = CharOuts.stdout;
            else {
                Writer scriptWriter = Files.getWriter(scriptFile, encoding);
                scriptOut = new WriterCharOut(scriptWriter);
            }
            try {
                CodeEmitter emitter = matchingLang.newEmitter(scriptOut); // options...
                parse(templateReader, emitter);
            } finally {
                templateReader.close();
                if (scriptOut != CharOuts.stdout)
                    scriptOut.close();
            }
        }
        L.detail("preprocess finished. ");
        if (compileOnly)
            return;

        L.detail("compile using local language ", matchingLang.getName());
        TemplateScript script = matchingLang.compile(scriptFile.getPath());

        L.detail("execute the compiled template");
        if (L.showDebug()) {
            L.debug("    with arguments: ");
            for (int i = 0; i < args.length; i++)
                L.debug("    ", i, ". \"", Strings.escape(args[i]), "\"");
        }
        InputStream resultStream = script.execute(args);
        OutputStream out = null;
        try {
            out = stdout ? System.out : new FileOutputStream(output);
            byte[] block = new byte[4096];
            int cb;
            while ((cb = resultStream.read(block)) != -1) {
                out.write(block, 0, cb);
            }
        } finally {
            resultStream.close();
            if (out != null && out != System.out)
                out.close();
        }
        L.detail("done");
    }

    static final int S_LEAD_TEXT = 0;
    static final int S_TEXT      = 1;
    static final int S_CODE      = 3;
    static final int S_EXPR      = 5;

    public static void parse(Reader reader, CodeEmitter emitter) throws IOException, ParseException {
        if (reader == null)
            throw new NullPointerException("reader");
        int state = S_TEXT;
        int braceLevel = 0;
        BCharOut buffer = new BCharOut();
        BCharOut leadingBuffer = new BCharOut();
        int y = 0;
        int x = 0;
        boolean atLeading = true;
        int c;
        while ((c = reader.read()) != -1) {
            if (c == '\n') {
                y++;
                x = 0;
            } else
                x++;
            if (c == ESCAPE) {
                c = reader.read();
                switch (c) {
                case CODE_GATE:
                case '{':
                case '}':
                    buffer._write(c);
                    continue;
                }
                buffer._write(ESCAPE);
            }
            switch (state) {
            case S_LEAD_TEXT:
                atLeading = x == 0;
                state = S_TEXT;
            case S_TEXT:
                if (atLeading)
                    if (Character.isSpaceChar(c)) {
                        leadingBuffer._write(c);
                        continue;
                    }
                atLeading = false;
                switch (c) {
                case CODE_GATE:
                    state = S_CODE;
                    leadingBuffer.flip(); // skip leading space to the gate.
                    break;
                case '{':
                    state = S_EXPR;
                    braceLevel++;
                    break;
                default:
                    buffer._write(c);
                    continue;
                }
                String text = buffer.flip();
                if (!text.isEmpty())
                    emitter.emitText(text);
                break;
            case S_CODE:
                switch (c) {
                case '\n':
                    buffer._write('\n');
                case CODE_GATE:
                    state = S_LEAD_TEXT;
                    break;
                default:
                    buffer._write(c);
                    continue;
                }
                String code = buffer.flip();
                if (!code.isEmpty())
                    emitter.emitCode(code);
                break;
            case S_EXPR:
                switch (c) {
                case '{':
                    braceLevel++;
                    break;
                case '}':
                    braceLevel--;
                    break;
                }
                if (braceLevel == 0) {
                    String exprCode = buffer.flip();
                    if (!exprCode.isEmpty())
                        emitter.emitExpr(exprCode);
                    state = S_TEXT;
                } else
                    buffer._write(c);
                break;
            default:
                throw new InvalidStateException();
            }
        }
        switch (state) {
        case S_LEAD_TEXT:
        case S_TEXT:
            String text = buffer.flip();
            if (!text.isEmpty())
                emitter.emitText(text);
            break;
        case S_CODE:
            String code = buffer.flip();
            if (!code.isEmpty())
                emitter.emitCode(code);
            break;
        case S_EXPR:
            throw new ParseException("expect '}': " + buffer);
        }
    }

    public static void main(String[] args) throws Exception {
        new XtxMain().run(args);
    }

    static final List<XtxLang> languages;
    static {
        languages = new ArrayList<XtxLang>();
        languages.add(new Perl());
        languages.add(new Python());
    }

}
