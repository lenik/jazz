package net.bodz.mda.tmpl.xtx;

import java.io.File;
import java.io.IOException;
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
import net.bodz.bas.io.Files;
import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.io.CharOuts.WriterCharOut;
import net.bodz.bas.lang.ControlExit;
import net.bodz.bas.lang.TRunnable;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.sg.InvalidStateException;
import net.bodz.mda.tmpl.xtx.langs.Perl;
import net.bodz.mda.tmpl.xtx.langs.Python;

@Doc("XTX Interpreter")
@ProgramName("xtx")
@RcsKeywords(id = "$Id$")
@Version( { 0, 1 })
public class Main extends BasicCLI {

    public static final int CODE_GATE = '\u00AC';                // ¬
    public static final int ESCAPE    = '\\';

    @Option(alias = "e", vnam = "CHARSET", doc = "encoding of template file")
    Charset                 encoding  = Charset.defaultCharset();

    @Option(alias = "c", vnam = "DIR", doc = "where to put compiled templates")
    File                    compileDirectory;

    @Option(alias = "d", vnam = "DIR", doc = "where to put result files, or directory")
    File                    output;

    @Option(vnam = "FILE", fileIndex = 0, required = true)
    File                    template;

    String                  templateNameWithoutXtx;
    File                    compiledFile;
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
            compileDirectory = template.getParentFile();
        compiledFile = new File(compileDirectory, templateNameWithoutXtx);

        if (output == null)
            output = compileDirectory;
        if (!output.isFile()) {
            String resultName = Files.getName(templateNameWithoutXtx);
            output = new File(output, resultName);
        }
    }

    @Override
    protected void doMainManaged(String[] args) throws Exception {
        String name = template.getName();
        XtxLang matchingLang = null;
        for (XtxLang lang : languages) {
            if (lang.matches(name)) {
                matchingLang = lang;
                break;
            }
        }
        if (matchingLang == null)
            throw new UnsupportedOperationException("No matching host lang for name: " + name);

        if (!compiledFile.exists() || //
                template.lastModified() > compiledFile.lastModified()) {
            Reader templateReader = Files.getReader(template, encoding);
            Writer compiledWriter = Files.getWriter(compiledFile, encoding);
            WriterCharOut cout = new WriterCharOut(compiledWriter);
            try {
                CodeEmitter emitter = matchingLang.newEmitter(cout); // options...
                parse(templateReader, emitter);
            } finally {
                templateReader.close();
                cout.close();
            }
        }

        TRunnable<String[], Exception> runnable = matchingLang.compile(compiledFile.getPath());
        if (runnable != null)
            try {
                runnable.run(args);
            } catch (ControlExit exit) {
                int status = exit.getStatus();
                System.exit(status);
            }
    }

    static final int S_TEXT = 0;
    static final int S_CODE = 1;
    static final int S_EXPR = 2;

    public static void parse(Reader reader, CodeEmitter emitter) throws IOException, ParseException {
        if (reader == null)
            throw new NullPointerException("reader");
        int state = S_TEXT;
        int braceLevel = 0;
        BCharOut buffer = new BCharOut();
        int c;
        while ((c = reader.read()) != -1) {
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
            case S_TEXT:
                switch (c) {
                case CODE_GATE:
                    state = S_CODE;
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
                emitter.emitText(text);
                break;
            case S_CODE:
                switch (c) {
                case '\n':
                    buffer._write('\n');
                case CODE_GATE:
                    state = S_TEXT;
                    break;
                default:
                    buffer._write(c);
                    continue;
                }
                String code = buffer.flip();
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
                buffer._write(c);
                if (braceLevel == 0) {
                    String exprCode = buffer.flip();
                    emitter.emitExpr(exprCode);
                    state = S_TEXT;
                }
                break;
            default:
                throw new InvalidStateException();
            }
        }
        switch (state) {
        case S_TEXT:
            String text = buffer.flip();
            emitter.emitText(text);
            break;
        case S_CODE:
            String code = buffer.flip();
            emitter.emitCode(code);
            break;
        case S_EXPR:
            throw new ParseException("expect '}': " + buffer);
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    static final List<XtxLang> languages;
    static {
        languages = new ArrayList<XtxLang>();
        languages.add(new Perl());
        languages.add(new Python());
    }

}
