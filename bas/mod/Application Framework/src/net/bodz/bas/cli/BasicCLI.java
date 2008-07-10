package net.bodz.bas.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

import net.bodz.bas.cli.util.Author;
import net.bodz.bas.cli.util.Doc;
import net.bodz.bas.cli.util.Rcs;
import net.bodz.bas.cli.util.RcsKeywords;
import net.bodz.bas.cli.util.Version;
import net.bodz.bas.cli.util.VersionInfo;
import net.bodz.bas.functors.lang.ControlBreak;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.annotations.OverrideOption;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.LogOut;
import net.bodz.bas.log.LogOuts;
import net.bodz.bas.types.AutoTypeMap;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.TypeParsers.ALogParser;
import net.bodz.bas.types.TypeParsers.CharOutParser;
import net.bodz.bas.types.util.Annotations;

/**
 * Recommend eclipse template `cli':
 * 
 * <pre>
 * ${:import(
 *     net.bodz.bas.cli.util.Doc, 
 *     net.bodz.bas.cli.util.RcsKeywords, 
 *     net.bodz.bas.cli.util.Version
 * )}@Doc(&quot;${primary_type_name} ${description}&quot;)
 * &#064;Version({ 0, 0 })
 * &#064;RcsKeywords(id = &quot;$$Id: ${primary_type_name}.java 0 ${date} ${time} ${user} $$&quot;)
 * public class ${primary_type_name} extends ${CLI:newType(net.bodz.bas.cli.BasicCLI)} {
 * 
 *     &#064;Override
 *     protected void _main(String[] args) throws Throwable {
 *         ${cursor}
 *     }
 * 
 *     public static void main(String[] args) throws Throwable {
 *         new ${primary_type_name}().climain(args);
 *     }
 * 
 * }
 * </pre>
 */
@RcsKeywords(id = "$Id: Rcs.java 784 2008-01-15 10:53:24Z lenik $")
@RunInfo(lib = "bodz_bas")
@OptionGroup(value = "standard", rank = -1)
public class BasicCLI {

    @Option(hidden = true, parser = CharOutParser.class)
    protected CharOut _stdout        = CharOuts.stdout;

    @Option(name = "logout", hidden = true, parser = ALogParser.class)
    protected ALog    L              = CLIConfig.getBootLog(ALog.INFO);

    @Option(hidden = true)
    protected String  _logPrefix     = "[" + getClass().getSimpleName() + "] ";

    @Option(hidden = true)
    protected boolean _logWithPrefix = true;

    @Option(hidden = true)
    protected boolean _logWithDate   = false;

    @Option(alias = "v", doc = "repeat to get more info")
    protected void _verbose() {
        L.setLevel(L.getLevel() + 1);
    }

    @Option(alias = "q", doc = "repeat to get less info")
    protected void _quiet() {
        L.setLevel(L.getLevel() - 1);
    }

    protected AutoTypeMap<String, Object> vars;

    @Option(name = "define", alias = ".D", vnam = "NAM=VAL", doc = "define variables")
    protected void define(String exp) throws ParseException {
        int eq = exp.indexOf('=');
        String nam = exp;
        Object val = null;
        if (eq != -1) {
            nam = exp.substring(0, eq);
            exp = exp.substring(eq + 1);
        }
        if (eq == -1)
            val = true;
        else {
            Class<?> type = getVarType(nam);
            TypeParser<?> parser = TypeParsers.guess(type);
            val = parser.parse(exp);
        }
        vars.put(nam, val);
    }

    protected Class<?> getVarType(String name) {
        return String.class;
    }

    protected VersionInfo _version;

    protected class CLILog extends ALog {

        public CLILog(int level) {
            super(level);
            HashMap<String, Object> hmap = new HashMap<String, Object>();
            vars = new AutoTypeMap<String, Object>(hmap);
        }

        @Override
        protected void register(String name, final LogOut out0) {
            LogOut out = out0;
            if (_logWithPrefix) {
                out = new LogOuts.Proxy(out0, "prefix-" + name) {
                    @Override
                    protected String prefix() {
                        if (_logWithDate) {
                            String time = CLIConfig.logTimeFormat.format(System
                                    .currentTimeMillis());
                            return _logPrefix + time;
                        }
                        return _logPrefix;
                    }
                };
            }
            super.register(name, out);
        }

    }

    public BasicCLI() {
        L = new CLILog(L.getLevel());
    }

    @Option(doc = "show version info")
    protected void _version() {
        Class<? extends BasicCLI> clazz = getClass();
        String name = clazz.getSimpleName();
        RcsKeywords keywords = clazz.getAnnotation(RcsKeywords.class);
        if (keywords != null) {
            _version = Rcs.parseId(keywords);
        } else {
            _version = new VersionInfo();
            URL url = Files.getClassResource(clazz);
            File file;
            try {
                file = new File(url.toURI());
            } catch (URISyntaxException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
            _version.name = name;
            _version.time = file.lastModified();
            _version.revision = new int[] { 0 };
        }

        int[] majorver = Annotations.getAnnotation(clazz, Version.class);
        if (majorver == null)
            majorver = new int[] { 0 };
        int[] verjoin = new int[majorver.length + _version.revision.length];
        System.arraycopy(majorver, 0, verjoin, 0, majorver.length);
        System.arraycopy(_version.revision, 0, verjoin, majorver.length,
                _version.revision.length);

        String author = Annotations.getAnnotation(clazz, Author.class);
        if (author != null)
            _version.author = author;

        if (_version.author == null)
            _version.author = "Caynoh";
        if (_version.state == null)
            _version.state = "UNKNOWN";

        String doc = Annotations.getAnnotation(clazz, Doc.class);
        if (doc == null)
            doc = clazz.getName();

        System.err.println(String.format("[%s] %s", name, doc));
        System.err.println(String.format(
                "Written by %s,  Version %s,  Last updated at %s",
                _version.author, VersionInfo.getVersion(verjoin), _version
                        .getDate()));
        throw new ControlBreak();
    }

    @Option(alias = ".h", doc = "show help info")
    protected void _help() throws CLIException {
        try {
            _version();
        } catch (ControlBreak b) {
        }
        System.err.println();
        String doc = ClassCLI.helpOptions(getClass(), 4, 29);
        System.err.print(doc);
        throw new ControlBreak();
    }

    @SuppressWarnings("unchecked")
    protected <T extends BasicCLI> ClassOptions<T> getClassOptions()
            throws CLIException {
        Class<T> clazz = (Class<T>) this.getClass();
        ClassOptions<T> opts = ClassCLI.getClassOptions(clazz);
        return opts;
    }

    public final void climain(String[] args) throws Throwable {
        Class<? extends BasicCLI> clazz = getClass();
        _RunInfo runInfo = _RunInfo.parse(clazz, true);

        runInfo.loadBoot();
        // runInfo.loadLibraries();

        ClassOptions<BasicCLI> opts = getClassOptions();
        try {
            args = opts.load(this, args);
            _boot();
            runInfo.loadExtras();

            if (L.showDebug()) {
                for (Entry<String, _Option<?>> entry : opts.getOptions()
                        .entrySet()) {
                    _Option<?> opt = entry.getValue();
                    String optnam = opt.getCanonicalName();
                    if (!optnam.equals(entry.getKey()))
                        continue;
                    Object optval = opt.get(this);
                    if (optval instanceof CallInfo)
                        continue;
                    L.d.P(optnam, " = ", Util.dispval(optval));
                }
                for (Entry<String, Object> entry : vars.entrySet()) {
                    String name = entry.getKey();
                    Object value = entry.getValue();
                    L.d.P("var ", name, " = ", value);
                }
            }
            _main(args);
            _exit();
        } catch (ControlBreak b) {
            return;
        }
    }

    protected void _boot() throws Throwable {
    }

    protected void _exit() throws Throwable {
        throw new ControlBreak("exit");
    }

    protected static final int CLI_AUTOSTDIN = 1;

    protected int _cliflags() {
        return CLI_AUTOSTDIN;
    }

    @OverrideOption(group = "basicMain")
    protected void _main(String[] args) throws Throwable {
        if (args.length == 0) {
            if ((_cliflags() & CLI_AUTOSTDIN) != 0)
                _main((File) null);
            else
                _help();
        } else
            for (String arg : args)
                _main(new File(arg));
    }

    /** if no argument specified, _main(null) is called. */
    @OverrideOption(group = "basicMain")
    protected void _main(File file) throws Throwable {
        InputStream in = file == null ? System.in : new FileInputStream(file);
        _main(file, in);
        if (file != null)
            in.close();
    }

    /** if no argument specified, _main(null, stdin) is called. */
    @OverrideOption(group = "basicMain")
    protected void _main(File file, InputStream in) throws Throwable {
        throw new NotImplementedException();
    }

}
