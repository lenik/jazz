package net.bodz.mda.rtl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.a.BootInfo;
import net.bodz.bas.cli.util.JavaLauncher;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.lang.err.CompileException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.Types;
import net.bodz.mda.xmm.initial.EnclosedTags;

@BootInfo(syslibs = "jflex")
public class JFlexCompiler extends JavaLauncher {

    /** clean generated files, default <code>true</code> */
    public boolean          clean        = true;

    // command-line options

    /** write generated file to &lt;directory&gt;, default is <code>$TMP</code> */
    public File             outdir;

    /** use external skeleton &lt;file&gt; */
    public File             skel;
    public boolean          skelOverride = true;

    public static final int CGM_DEFAULT  = 0;
    public static final int CGM_SWITCH   = 1;
    public static final int CGM_TABLE    = 2;
    public static final int CGM_PACK     = 3;
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

    public int              verbose      = 0;

    // not command-line options

    /** recognize EOF, yylex() returns int rather then Yytoken object */
    public boolean          f_byaccj     = true;

    public boolean          f_unicode    = true;
    public boolean          f_line       = true;
    public boolean          f_column     = true;
    public String           f_type;
    public boolean          f_int        = true;
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
            Class<?> clazz = getMainClass();
            Field versionField = Reflects.getField(clazz, "version");
            String version = (String) Reflects.get(null, versionField);
            URL skelURL = Files.classData(JFlexCompiler.class, "skel_" + version);
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
            if (_skel != skel) {
                // it seems JFlex didn't close the skeleton properly.
                // but we just don't care.
                // System.gc();
                _skel.delete();
            }
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
        File lexFile = Files.canoniOf(lexDir, "JFlexCompiler_" + (++lexId) + ".l");
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

    public static class CompileResult {
        public String code;
        public String dotGraph;
    }

    public CompileResult compile(String header, String macroDecl, String ruleDef)
            throws CompileException {
        BCharOut all = new BCharOut();
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

        CompileResult result = new CompileResult();

        // FQCN subdir...
        File lexImplFile = new File(outdir, f_classSimpleName + ".java");
        // File dotFile = new File(outdir, "dot.graphviz");

        try {
            if (lexImplFile.exists())
                result.code = readImplOverride(lexImplFile);
        } catch (IOException e) {
            throw new CompileException(e.getMessage(), e);
        } finally {
            if (clean) {
                lexImplFile.delete();
            }
        }
        return result;
    }

    public static String implClassNameSuffix = "Impl";

    public static String getImplClassName(Class<?> lexerModel) {
        String cname = lexerModel.getName();
        cname = cname.replace('$', '_');
        return cname + implClassNameSuffix;
    }

    public CompileResult compile(Class<?> lexerModel) throws CompileException {
        String genClassName = getImplClassName(lexerModel);
        JFlexFormatter formatter = new JFlexFormatter(lexerModel, genClassName);
        String header = formatter.getHeader();
        String decl = formatter.getDecl();
        String body = formatter.getBody();

        f_classSimpleName = formatter.getSimpleName();
        f_classExtends = formatter.getSimpleSuperName();
        // f_initthrow = null; // reflect
        Class<?>[] rulesExceptions = formatter.getRulesExceptions();
        if (rulesExceptions.length != 0)
            f_yylexthrow = Types.joinNames(", ", true, rulesExceptions);
        return compile(header, decl, body);
    }

    static final Map<String, String> tagMap;
    static final EnclosedTags        prep;
    static final Pattern             killAnchor;
    static {
        tagMap = new HashMap<String, String>();
        tagMap.put("delete", "");
        prep = new EnclosedTags(tagMap);
        killAnchor = Pattern.compile("//\\s*<kill=(\\d+)>\\s*$", Pattern.MULTILINE);
    }

    static String killLines(String s, int off, int lines) {
        int end = off;
        while (lines-- > 0) {
            end = s.indexOf('\n', end);
            if (end == -1) {
                end = s.length();
                break;
            }
            end++; // eat the '\n'.
        }
        String head = s.substring(0, off);
        String foot = s.substring(end);
        return head + foot;
    }

    public String readImplOverride(File f) throws IOException {
        String s = Files.readAll(f);
        if (skelOverride) {
            // s = prep.process(s);
            Matcher m = killAnchor.matcher(s);
            if (m.find()) {
                // +1 for tag itself
                int kills = Integer.valueOf(m.group(1)) + 1;
                s = killLines(s, m.start(), kills);
            }
        }
        return s;
    }
}
