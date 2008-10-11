package net.bodz.bas.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.bodz.bas.a.A_bas;
import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.VersionInfo;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.cli.a.OptionGroup;
import net.bodz.bas.cli.a.RunInfo;
import net.bodz.bas.cli.ext.CLIPlugin;
import net.bodz.bas.cli.ext.CLIPlugins;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.a.ChainUsage;
import net.bodz.bas.lang.a.OverrideOption;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.lang.script.ScriptClass;
import net.bodz.bas.lang.script.ScriptException;
import net.bodz.bas.lang.script.ScriptType;
import net.bodz.bas.lang.script.Scripts;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.LogOut;
import net.bodz.bas.log.LogOuts;
import net.bodz.bas.mod.plugins.PluginException;
import net.bodz.bas.mod.plugins.PluginTypeEx;
import net.bodz.bas.types.AutoTypeMap;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.TypeParsers.ALogParser;
import net.bodz.bas.types.TypeParsers.CharOutParser;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.Strings;

/**
 * Recommend eclipse template `cli':
 * 
 * <pre>
 * ${:import(
 *     net.bodz.bas.a.Doc, 
 *     net.bodz.bas.a.RcsKeywords, 
 *     net.bodz.bas.a.Version
 * )}@Doc(&quot;${primary_type_name} ${description}&quot;)
 * &#064;Version({ 0, 0 })
 * &#064;RcsKeywords(id = &quot;$$Id: ${primary_type_name}.java 0 ${date} ${time} ${user} $$&quot;)
 * public class ${primary_type_name} extends ${CLI:newType(net.bodz.bas.cli.BasicCLI)} {
 * 
 *     &#064;Override
 *     protected void doFileArgument(File file, InputStream in) throws Throwable {
 *         ${cursor}
 *     }
 * 
 *     public static void main(String[] args) throws Throwable {
 *         new ${primary_type_name}().run(args);
 *     }
 * 
 * }
 * </pre>
 * 
 * @see ClassOptions
 * @see Option
 */
@RcsKeywords(id = "$Id: Rcs.java 784 2008-01-15 10:53:24Z lenik $")
@RunInfo(lib = "bodz_bas")
@OptionGroup(value = "standard", rank = -1)
@ScriptType(CLIScriptClass.class)
public class BasicCLI {

    static {
        CLIConfig.load();
    }

    @Option(hidden = true, parser = CharOutParser.class)
    protected CharOut _stdout        = CharOuts.stdout;

    @Option(name = "logout", hidden = true, parser = ALogParser.class)
    protected ALog    L;

    @Option(hidden = true)
    String            _logPrefix     = "[" + getClass().getSimpleName() + "] ";

    @Option(hidden = true)
    boolean           _logWithPrefix = true;

    @Option(hidden = true)
    boolean           _logWithDate   = false;

    @Option(alias = "v", doc = "repeat to get more info")
    void _verbose() {
        L.setLevel(L.getLevel() + 1);
    }

    @Option(alias = "q", doc = "repeat to get less info")
    void _quiet() {
        L.setLevel(L.getLevel() - 1);
    }

    protected AutoTypeMap<String, Object> _vars;

    @Option(name = "define", alias = ".D", vnam = "NAM=VAL", doc = "define variables")
    void _define(String exp) throws CreateException, ParseException {
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
            Class<?> type = _getVarType(nam);
            TypeParser parser = TypeParsers.guess(type);
            val = parser.parse(exp);
        }
        _vars.put(nam, val);
    }

    protected Class<?> _getVarType(String name) {
        return String.class;
    }

    protected final CLIPlugins plugins = new CLIPlugins();

    private class PluginParser implements TypeParser {

        @Override
        public boolean variant() {
            return true;
        }

        @Override
        public CLIPlugin parse(String idCtor) throws ParseException {
            int eq = idCtor.indexOf('=');
            String id = idCtor;
            String arg = null;
            if (eq != -1) {
                id = idCtor.substring(0, eq);
                arg = idCtor.substring(eq + 1);
            }
            try {
                CLIPlugin plugin = create(id, arg);
                plugin.setParameters(_vars);
                return plugin;
            } catch (PluginException e) {
                throw new ParseException(e.getMessage(), e);
            } catch (CreateException e) {
                throw new ParseException(e.getMessage(), e);
            } catch (CLIException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

        private CLIPlugin create(String pluginId, String ctorArg)
                throws CreateException, PluginException, ParseException {
            PluginTypeEx typeEx = plugins.getTypeEx(CLIPlugin.class, pluginId);
            if (typeEx == null)
                throw new PluginException("no plugin of " + pluginId);
            Class<?> clazz = typeEx.getType();
            Constructor<?>[] ctors = clazz.getConstructors(); // only get public
            Constructor<?> ctor = null;
            Class<?>[] sigMaxLen = null;
            for (Constructor<?> _ctor : ctors) {
                Class<?>[] _sig = _ctor.getParameterTypes();
                if (ctor == null || _sig.length > sigMaxLen.length) {
                    ctor = _ctor;
                    sigMaxLen = _sig;
                }
            }
            if (ctor == null)
                throw new PluginException("no constructor in " + clazz);
            int len = sigMaxLen.length;
            int off = 0;
            if (clazz.isMemberClass()) {
                len--;
                off++;
            }
            if (len == 0) {
                assert ctorArg == null;
                return (CLIPlugin) typeEx.newInstance();
            }
            if (len != 1)
                throw new PluginException("no suitable constructor to use: "
                        + typeEx);

            Class<?> sig0 = sigMaxLen[off];
            if (sig0 == String.class)
                return (CLIPlugin) typeEx.newInstance(ctorArg);
            if (!sig0.isArray()) {
                TypeParser parser = TypeParsers.guess(sig0);
                Object val = parser.parse(ctorArg);
                return (CLIPlugin) typeEx.newInstance(val);
            }

            // ctor(E[] array)
            String[] args = {};
            if (ctorArg != null)
                args = ctorArg.split(",");
            Class<?> valtype = sig0.getComponentType();
            if (valtype == String.class)
                return (CLIPlugin) typeEx.newInstance((Object) args);

            TypeParser parser = TypeParsers.guess(valtype);
            Object valarray = Array.newInstance(valtype, args.length);
            for (int i = 0; i < args.length; i++) {
                Object val = parser.parse(args[i]);
                Array.set(valarray, i, val);
            }
            return (CLIPlugin) typeEx.newInstance((Object) valarray);
        }
    }

    protected class CLILog extends ALog {

        public CLILog(int level) {
            super(level);
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

    private ClassInfo _classInfo;

    protected ClassInfo _loadClassInfo() {
        if (_classInfo == null) {
            Class<? extends BasicCLI> clazz = getClass();
            ClassInfo info = ClassInfo.get(clazz);

            String name = clazz.getSimpleName();
            String doc = info.getDoc();
            if (doc == null)
                doc = clazz.getName();

            int[] majorver = info.getVersion();
            if (majorver == null)
                majorver = new int[] { 0 };

            RcsKeywords keywords = clazz.getAnnotation(RcsKeywords.class);
            VersionInfo verinfo;
            if (keywords != null) {
                verinfo = A_bas.parseId(keywords);
            } else {
                verinfo = new VersionInfo();
                URL url = Files.classData(clazz);
                File file;
                try {
                    file = new File(url.toURI());
                } catch (URISyntaxException e) {
                    throw new UnexpectedException(e.getMessage(), e);
                }
                verinfo.name = name;
                verinfo.time = file.lastModified();
                verinfo.revision = new int[] { 0 };
            }

            int[] verjoin = new int[majorver.length + verinfo.revision.length];
            System.arraycopy(majorver, 0, verjoin, 0, majorver.length);
            System.arraycopy(verinfo.revision, 0, verjoin, majorver.length,
                    verinfo.revision.length);

            String author = info.getAuthor();
            if (author != null)
                verinfo.author = author;
            else
                author = verinfo.author;
            if (author == null)
                author = "Caynoh";
            if (verinfo.state == null)
                verinfo.state = "UNKNOWN";

            info.setName(name);
            info.setDoc(doc);
            info.setAuthor(author);
            info.setVersion(verjoin);
            info.setDateString(verinfo.getDate());
            _classInfo = info;
        }
        return _classInfo;
    }

    @Option(doc = "show version info")
    protected final void _version() {
        _version(CharOuts.stderr);
        throw new ControlBreak();
    }

    protected void _version(CharOut out) {
        ClassInfo info = _loadClassInfo();
        out.printf("[%s] %s\n", info.getName(), info.getDoc());
        out.printf("Written by %s,  Version %s,  Last updated at %s\n", //
                info.getAuthor(), //
                Strings.joinDot(info.getVersion()), //
                info.getDateString());
    }

    @Option(alias = ".h", doc = "show help info")
    protected final void _help() throws CLIException {
        _help(CharOuts.stderr);
        throw new ControlBreak();
    }

    protected void _help(CharOut out) throws CLIException {
        _version(out);
        out.println();

        String hlp_opts = ClassCLI.helpOptions(getClass(), 4, 29);
        out.print(hlp_opts);

        if (plugins != null)
            plugins.help(out, "");
    }

    private boolean                prepared;
    private _RunInfo               runInfo;
    private ClassOptions<BasicCLI> opts;
    private List<String>           restArgs;

    public BasicCLI() {
        TypeParsers.register(CLIPlugin.class, new PluginParser());
        String loglevel = System.getProperty(CLIConfig.PROPERTY_LOGLEVEL);
        int bootLevel = loglevel == null ? ALog.INFO : Integer
                .parseInt(loglevel);
        L = CLIConfig.getBootLog(bootLevel);
        HashMap<String, Object> hmap = new HashMap<String, Object>();
        _vars = new AutoTypeMap<String, Object>(hmap);
    }

    public ScriptClass<? extends BasicCLI> getScriptClass()
            throws ScriptException {
        return Scripts.getScriptClass(this);
    }

    @SuppressWarnings("unchecked")
    public <T extends BasicCLI> ClassOptions<T> getOptions()
            throws CLIException {
        Class<T> clazz = (Class<T>) this.getClass();
        ClassOptions<T> opts = ClassCLI.getClassOptions(clazz);
        return opts;
    }

    private void _prepare() throws CLIException {
        if (prepared)
            return;

        L.x.P("cli parse runinfo");
        runInfo = _RunInfo.parse(getClass(), true);

        L.x.P("cli load boot");
        runInfo.loadBoot();

        if (!"1".equals(System.getProperty(CLIConfig.PROPERTY_LIB_LOADED))) {
            L.x.P("cli load libraries");
            runInfo.loadLibraries();
        }

        L.x.P("cli get options");
        opts = getOptions();

        L.x.P("drop the boot logger");
        L = new CLILog(L.getLevel());

        restArgs = new ArrayList<String>();
        prepared = true;
    }

    public void addArguments(String... args) throws CLIException,
            ParseException {
        _prepare();
        String[] rest = opts.load(this, args);
        for (String arg : rest)
            restArgs.add(arg);
    }

    public void run(String cmdline) throws Throwable {
        String[] args = {};
        if (cmdline != null)
            args = Strings.split(cmdline);
        run(args);
    }

    /**
     * public access: so derivations don't have to declare static main()s.
     */
    public final synchronized void run(String... args) throws Throwable {
        L.x.P("cli prepare");
        _prepare();
        int preRestSize = restArgs.size(); // make climain() reentrant.

        try {
            addArguments(args);

            L.x.P("cli boot");
            _postInit();
            _boot();

            L.x.P("cli load delayed");
            runInfo.loadDelayed();

            if (L.showDebug()) {
                for (Entry<String, _Option<?>> entry : opts.getOptions()
                        .entrySet()) {
                    _Option<?> opt = entry.getValue();
                    String optnam = opt.getCLIName();
                    if (!optnam.equals(entry.getKey()))
                        continue;
                    Object optval = opt.get(this);
                    if (optval instanceof CallInfo)
                        continue;
                    L.d.P(optnam, " = ", Util.dispval(optval));
                }
                for (Entry<String, Object> entry : _vars.entrySet()) {
                    String name = entry.getKey();
                    Object value = entry.getValue();
                    L.d.P("var ", name, " = ", value);
                }
            }
            String[] rest = restArgs.toArray(Empty.Strings);

            L.x.P("cli main");
            _main(rest);

            L.x.P("cli exit");
            _exit();
        } catch (ControlBreak b) {
            return;
        } finally {
            while (restArgs.size() > preRestSize)
                restArgs.remove(restArgs.size() - 1);
        }
    }

    /** Do nothing, to be overrided. */
    @OverrideOption(chain = ChainUsage.PREFERRED)
    void _postInit() throws Throwable {
    }

    /** Do nothing, to be overrided. */
    protected void _boot() throws Throwable {
    }

    /**
     * @throws ControlBreak
     */
    protected void _exit() throws Throwable {
        throw new ControlBreak("exit");
    }

    /**
     * return non-null value to enable the `<i>default file is read from
     * stdin</i>' mode.
     * 
     * @return <code>null</code>
     */
    protected InputStream _getDefaultIn() {
        return null;
    }

    /**
     * User main method.
     * 
     * The --options have been parsed.
     */
    @OverrideOption(group = "basicMain")
    protected void _main(String[] args) throws Throwable {
        if (args.length == 0) {
            InputStream in = _getDefaultIn();
            if (in != null)
                doFileArgument(null, in);
            else
                _help();
        } else {
            doMain(args);
        }
    }

    /**
     * User repeatable-main method.
     * 
     * stdin is never used.
     */
    @OverrideOption(group = "basicMain")
    protected void doMain(String[] args) throws Throwable {
        for (String arg : args)
            doFileArgument(Files.canoniOf(arg));
    }

    /**
     * @param file
     *            canonical file
     */
    @OverrideOption(group = "basicMain")
    protected void doFileArgument(File file) throws Throwable {
        assert file != null;
        FileInputStream in = new FileInputStream(file);
        try {
            doFileArgument(file, in);
        } finally {
            in.close();
        }
    }

    /**
     * if no argument specified, _main(null, stdin) is called.
     * 
     * @param file
     *            canonical file or null
     */
    @OverrideOption(group = "basicMain")
    protected void doFileArgument(File file, InputStream in) throws Throwable {
        throw new NotImplementedException();
    }

}
