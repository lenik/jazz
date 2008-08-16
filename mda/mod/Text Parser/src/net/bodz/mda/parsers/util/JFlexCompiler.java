package net.bodz.mda.parsers.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.cli.RunInfo;
import net.bodz.bas.cli.util.JavaLauncher;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.err.CompileException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.Types;

@RunInfo(lib = "jflex")
public class JFlexCompiler extends JavaLauncher {

    /** clean generated files, default <code>true</code> */
    public boolean          clean       = true;

    // command-line options

    /** write generated file to &lt;directory&gt;, default is <code>$TMP</code> */
    public File             outdir;

    /** use external skeleton &lt;file&gt; */
    public File             skel;
    private boolean         skelOverride;

    public static final int CGM_DEFAULT = 0;
    public static final int CGM_SWITCH  = 1;
    public static final int CGM_TABLE   = 2;
    public static final int CGM_PACK    = 3;
    public int              codeGenMethod;

    /** strict JLex compatibility */
    public boolean          jlexCompat;

    /** skip minimization step */
    public boolean          nomin;

    /** create backup files */
    public boolean          bak;

    /** display transition tables */
    public boolean          dump;

    /** write graphviz .dot files for the generated automata (alpha) */
    public boolean          dot;

    public int              verbose     = 0;

    // not command-line options

    /** recognize EOF, yylex() returns int rather then Yytoken object */
    public boolean          f_byaccj    = true;

    public boolean          f_unicode   = true;
    public boolean          f_line      = true;
    public boolean          f_column    = true;
    public String           f_type;
    public boolean          f_int       = true;
    /** %init { ... } */
    public String           f_init;
    /** %initthrow { exception1 [, ...] } */
    public String           f_initthrow;
    public String           f_scanerror;
    /** default yylex */
    public String           f_funcname;
    /** public, protected, or private */
    public String           f_classVisible;
    public String           f_classSimpleName;
    public String           f_classExtends;
    /** %implements { intf [, ...] } */
    public String           f_classImplements;
    /** %yylexthrow ParseException */
    public String           f_yylexthrow;

    public JFlexCompiler(File outdir, boolean clean) {
        this.outdir = outdir;
        this.clean = clean;
    }

    public JFlexCompiler() {
        this(Files.getTmpDir(), true);
    }

    @Override
    protected String getMainClassName() {
        return "JFlex.Main";
    }

    public void compile(File lexFile) throws CompileException {
        List<String> args = new ArrayList<String>();
        if (outdir != null) {
            args.add("-d");
            args.add(outdir.getPath());
        }
        File _skel = skel;
        if (_skel == null && skelOverride) {
            URL skelURL = Files.classData(JFlexCompiler.class, "skel_1.4.1");
            try {
                String skelData = Files.readAll(skelURL, "ascii");
                String preferredFileName = Files.getName(skelURL.getFile());
                _skel = Files.canoniOf(Files.getTmpDir(), preferredFileName);
                Files.write(_skel, skelData, "ascii");
            } catch (IOException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
        }
        if (_skel != null) {
            args.add("--skel");
            args.add(_skel.getPath());
        }
        if (codeGenMethod != CGM_DEFAULT)
            switch (codeGenMethod) {
            case CGM_SWITCH:
                args.add("--switch");
                break;
            case CGM_TABLE:
                args.add("--table");
                break;
            case CGM_PACK:
                args.add("--pack");
                break;
            default:
                throw new IllegalArgumentException("codeGenMethod");
            }
        if (jlexCompat)
            args.add("--jlex");
        if (nomin)
            args.add("--nomin");
        if (!bak)
            args.add("--nobak");
        if (dump)
            args.add("--dump");
        if (dot)
            args.add("--dot");
        if (verbose < 0)
            args.add("-q");
        if (verbose > 0)
            args.add("-v");

        args.add(lexFile.getPath());

        try {
            String[] argv = args.toArray(Empty.Strings);
            launch(argv);
        } catch (Exception e) {
            throw new CompileException(e.getMessage(), e);
        } finally {
            if (_skel != skel)
                _skel.delete();
        }
    }

    @Override
    protected void _exit(int n) {
        // System.err.println("JFlex abnormally exited, with status = " + n);
    }

    private static int lexId = 0;

    public void compile(String lexDef) throws CompileException {
        File lexDir = outdir;
        if (lexDir == null)
            lexDir = Files.getTmpDir();
        File lexFile = Files.canoniOf(lexDir, "JFlexCompiler_" + (++lexId)
                + ".l");
        try {
            Files.write(lexFile, lexDef);
            compile(lexFile);
        } catch (IOException e) {
            throw new CompileException(e.getMessage(), e);
        } finally {
            if (clean) {
                lexFile.delete();
            }
        }
    }

    public static final int R_IMPL = 0;
    public static final int R_DOT  = 1;

    public String[] compile(String header, String macroDecl, String ruleDef)
            throws CompileException {
        Buffer all = new Buffer();
        if (header != null) {
            all.print(header);
            if (!header.endsWith("\n"))
                all.println();
        }

        all.println("%%");
        if (f_byaccj)
            all.println("%byaccj");
        if (f_unicode)
            all.println("%unicode");
        if (f_line)
            all.println("%line");
        if (f_column)
            all.println("%column");
        if (f_type != null)
            all.println("%type " + f_type);
        if (f_int)
            all.println("%int");
        if (f_init != null)
            all.println("%init {" + f_init + "}");
        if (f_initthrow != null)
            all.println("%initthrow {" + f_initthrow + "}");
        if (f_scanerror != null)
            all.println("%scanerror " + f_scanerror);
        if (f_funcname != null)
            all.println("%funcname " + f_funcname);
        if (f_classVisible != null)
            all.println("%" + f_classVisible);
        if (f_classSimpleName != null)
            all.println("%class " + f_classSimpleName);
        if (f_classExtends != null)
            all.println("%extends " + f_classExtends);
        if (f_classImplements != null)
            all.println("%implements {" + f_classImplements + "}");
        if (f_yylexthrow != null)
            all.println("%yylexthrow " + f_yylexthrow);
        if (macroDecl != null)
            all.println(macroDecl);

        all.println("%%");
        if (ruleDef != null)
            all.println(ruleDef);

        // if (footer != null) {
        // all.println("%%");
        // all.print(footer);
        // }

        compile(all.toString());

        String[] result = new String[4];

        // FQCN subdir...
        File lexImplFile = new File(outdir, f_classSimpleName + ".java");
        // File dotFile = new File(outdir, "dot.graphviz");

        try {
            if (lexImplFile.exists())
                result[R_IMPL] = Files.readAll(lexImplFile);
        } catch (IOException e) {
            throw new CompileException(e.getMessage(), e);
        } finally {
            if (clean) {
                lexImplFile.delete();
            }
        }
        return result;
    }

    public String[] compile(Class<?> lexerModel) throws CompileException {
        String genSuffix = "Impl";
        String lexerClassName = lexerModel.getName() + genSuffix;
        JFlexFormatter formatter = new JFlexFormatter(lexerModel,
                lexerClassName);
        String header = formatter.getHeader();
        String decl = formatter.getDecl();
        String body = formatter.getBody();

        f_classSimpleName = lexerModel.getSimpleName() + genSuffix;
        f_classExtends = lexerModel.getSimpleName(); // reflect
        // f_initthrow = null; // reflect
        f_yylexthrow = Types.joinNames(", ", true, formatter
                .getRulesExceptions());
        return compile(header, decl, body);
    }

}
