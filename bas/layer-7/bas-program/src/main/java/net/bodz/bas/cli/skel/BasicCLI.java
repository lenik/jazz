package net.bodz.bas.cli.skel;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptException;
import javax.swing.text.html.Option;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.cli.ClassOptions;
import net.bodz.bas.cli.Util;
import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.cli.model.MethodCall;
import net.bodz.bas.cli.model.plugin.CLIPlugin;
import net.bodz.bas.cli.model.plugin.CLIPlugins;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.ControlExit;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.api.Logger;
import net.bodz.bas.log.api.LoggerFactory;
import net.bodz.bas.meta.build.ClassInfo;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.meta.build.RcsKeywordsUtil;
import net.bodz.bas.meta.build.VersionInfo;
import net.bodz.bas.meta.codehint.ChainUsage;
import net.bodz.bas.meta.codehint.OverrideOption;
import net.bodz.bas.meta.program.OptionGroup;
import net.bodz.bas.model.IExecutableVarArgsX;
import net.bodz.bas.potato.book.DisplayNameUtil;
import net.bodz.bas.potato.traits.IType;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ParserUtil;
import net.bodz.bas.ui.ConsoleUI;
import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.util.PluginException;
import net.bodz.bas.util.PluginTypeEx;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.SystemColos;

import org.apache.commons.lang.ArrayUtils;

/**
 * Recommend eclipse template `cli':
 * 
 * <pre>
 * ${:import(
 *     net.bodz.bas.a.Doc,
 *     net.bodz.bas.a.RcsKeywords,
 *     net.bodz.bas.a.Version
 * )}@Doc(&quot;${primary_type_name} ${description}&quot;)
 * &#064;RcsKeywords(id = &quot;$$Id${primary_type_name}.java 0 ${date} ${time} ${user} $$&quot;)
 * &#064;Version({ 0, 0 })
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
 * @see OptionGroup
 * @see Option
 */
@OptionGroup(value = "standard", rank = -1)
@RcsKeywords(id = "$Id$")
public class BasicCLI
        implements Runnable, IExecutableVarArgsX<String, Exception> {

    /**
     * @option --stdout hidden weak
     */
    protected IPrintOut _stdout = Stdio.cout;

    /**
     * @option --logger hidden
     */
    protected Logger L = LoggerFactory.getLogger(BasicCLI.class);
    // LogTerms.resolveFile(1);

    protected UserInterface UI = ConsoleUI.stdout;

    /**
     * @option hidden
     */
    boolean _logWithPrefix = true;

    /**
     * @option hidden
     */
    boolean _logWithDate = false;

    /**
     * Repeat to get more info.
     * 
     * @option -v
     */
    void _verbose() {
        L.verbose(1);
    }

    /**
     * Repeat to get less info.
     * 
     * @option -q
     */
    void _quiet() {
        L.verbose(-1);
    }

    protected Map<String, Object> _vars;

    /**
     * Define variable.
     * 
     * @option -D --define =NAM=VAL
     */
    void _define(String exp)
            throws ParseException {
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
            val = ParserUtil.parse(type, exp);
        }
        _vars.put(nam, val);
    }

    protected Class<?> _getVarType(String name) {
        return String.class;
    }

    protected final CLIPlugins plugins = new CLIPlugins();

    private class PluginParser
            extends AbstractParser<CLIPlugin> {

        @Override
        public CLIPlugin parse(String idCtor)
                throws ParseException {
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
                throw new PluginException("no suitable constructor to use: " + typeEx);

            Class<?> sig0 = sigMaxLen[off];
            if (sig0 == String.class)
                return (CLIPlugin) typeEx.newInstance(ctorArg);
            if (!sig0.isArray()) {
                Object val = ParserUtil.parse(sig0, ctorArg);
                return (CLIPlugin) typeEx.newInstance(val);
            }

            // ctor(E[] array)
            String[] args = {};
            if (ctorArg != null)
                args = ctorArg.split(",");
            Class<?> valtype = sig0.getComponentType();
            if (valtype == String.class)
                return (CLIPlugin) typeEx.newInstance((Object) args);

            IParser<?> parser = Traits.getTrait(valtype, IParser.class);
            Object valarray = Array.newInstance(valtype, args.length);
            for (int i = 0; i < args.length; i++) {
                Object val = parser.parse(args[i]);
                Array.set(valarray, i, val);
            }
            return (CLIPlugin) typeEx.newInstance(valarray);
        }
    }

    private ClassInfo _classInfo;

    protected ClassInfo _loadClassInfo() {
        if (_classInfo == null) {
            Class<? extends BasicCLI> clazz = getClass();
            ClassInfo info = ClassInfo.get(clazz);

            String name = DisplayNameUtil.getDisplayName(clazz);
            String doc = info.getDoc();
            if (doc == null)
                doc = clazz.getName();

            int[] majorver = info.getVersion();
            if (majorver == null)
                majorver = new int[] { 0 };

            RcsKeywords keywords = clazz.getAnnotation(RcsKeywords.class);
            VersionInfo verinfo;
            if (keywords != null) {
                verinfo = RcsKeywordsUtil.getVersionInfo(keywords);
            } else {
                verinfo = new VersionInfo();
                URL url = ClassResource.classDataURL(clazz);
                try {
                    File file = new File(url.toURI());
                    verinfo.time = file.lastModified();
                } catch (Exception e) {
                    // (neither RCS.time nor File.time exists)
                    // throw new UnexpectedException("Bad url: " + url, e);
                    verinfo.time = System.currentTimeMillis();
                }
                verinfo.name = name;
                verinfo.revision = new int[] { 0 };
            }

            int[] verjoin = new int[majorver.length + verinfo.revision.length];
            System.arraycopy(majorver, 0, verjoin, 0, majorver.length);
            System.arraycopy(verinfo.revision, 0, verjoin, majorver.length, verinfo.revision.length);

            String author = info.getAuthor();
            if (author != null)
                verinfo.author = author;
            else
                author = verinfo.author;
            if (author == null)
                author = "Lenik";
            if (verinfo.state == null)
                verinfo.state = "UNKNOWN";

            info.setName(name);
            info.setDoc(doc);
            info.setAuthor(author);
            // if (info.getVersion() == null) // FIX
            info.setVersion(verjoin);
            info.setDateString(verinfo.getDate());
            _classInfo = info;
        }
        return _classInfo;
    }

    /**
     * Show version information
     * 
     * @option
     */
    protected final void _version() {
        _version(Stdio.cerr);
        throw new ControlBreak();
    }

    protected void _version(IPrintOut out) {
        ClassInfo info = _loadClassInfo();
        out.printf("[%s] %s\n", info.getName(), info.getDoc());
        out.printf("Written by %s,  Version %s,  Last updated at %s\n", //
                info.getAuthor(), //
                StringArray.joinByDot(info.getVersion()), //
                info.getDateString());
    }

    /**
     * Show this help text.
     * 
     * @option -h weak
     */
    protected final void _help()
            throws CLIException {
        _help(Stdio.cerr);
        throw new ControlBreak();
    }

    protected void _help(IPrintOut out)
            throws CLIException {
        _version(out);
        out.println();

        String hlp_opts = ClassOptions.helpOptions(getClass(), _helpRestSyntax(), 4, 29);
        out.print(hlp_opts);

        if (plugins != null)
            plugins.help(out, "");

        out.flush();
    }

    protected String _helpRestSyntax() {
        return "FILES";
    }

    private boolean prepared;
    // private BootProc bootProc;
    private IOptionGroup opts;
    private List<String> restArgs;

    public BasicCLI() {
        _vars = new HashMap<String, Object>();
    }

    public IType getScriptClass()
            throws ScriptException {
        return Traits.getTrait(getClass(), IType.class);
    }

    @SuppressWarnings("unchecked")
    public <T extends BasicCLI> IOptionGroup getOptions()
            throws CLIException {
        Class<T> clazz = (Class<T>) this.getClass();
        IOptionGroup opts = ClassOptions.getClassOptions(clazz);
        return opts;
    }

    private void _prepare()
            throws CLIException {
        if (prepared)
            return;

        ILogSink dbg = L.getDebugSink();
        // dbg.p("parse boot info");
        // bootProc = BootProc.get(getClass());
        //
        // if (bootProc != null) {
        // dbg.p("load-config PRE");
        // try {
        // bootProc.load(Integer.MIN_VALUE, 0);
        // } catch (LoadException e) {
        // throw new CLIException(e);
        // }
        // }

        dbg.p("cli get options");
        opts = getOptions();

        restArgs = new ArrayList<String>();
        prepared = true;
    }

    public void sendArguments(String... args)
            throws CLIException, ParseException {
        _prepare();
        String[] rejected = opts.load(this, args);
        for (String arg : rejected)
            restArgs.add(arg);
    }

    public void runExtra(String cmdline)
            throws Throwable {
        String[] args = {};
        if (cmdline != null)
            args = StringArray.split(cmdline);
        execute(args);
    }

    @Override
    public void run() {
        try {
            execute(new String[0]);
        } catch (Exception e) {
            UI.alert(e.getMessage(), e);
        }
    }

    /**
     * public access: so derivations don't have to declare static main()s.
     */
    @Override
    public synchronized void execute(String... args)
            throws Exception {
        ILogSink dbg = L.getDebugSink();
        dbg.p("cli prepare");
        _prepare();
        int preRestSize = restArgs.size(); // make climain() reentrant.

        try {
            sendArguments(args);

            dbg.p("cli boot");
            _postInit();
            _boot();

            // if (bootProc != null) {
            // t.p("load-config post");
            // try {
            // bootProc.load(0, Integer.MAX_VALUE);
            // } catch (LoadException e) {
            // throw new CLIException(e);
            // }
            // }

            if (L.isDebugEnabled()) {
                for (Entry<String, IOption> entry : opts.getOptions().entrySet()) {
                    IOption opt = entry.getValue();
                    String optnam = opt.getFriendlyName();
                    if (!optnam.equals(entry.getKey()))
                        continue;
                    Object optval = opt.resolveFile(this);
                    if (optval instanceof MethodCall)
                        continue;
                    dbg.p(optnam, " = ", Util.dispval(optval));
                }
                for (Entry<String, Object> entry : _vars.entrySet()) {
                    String name = entry.getKey();
                    Object value = entry.getValue();
                    dbg.p("var ", name, " = ", value);
                }
            }
            String[] rest = restArgs.toArray(ArrayUtils.EMPTY_STRING_ARRAY);

            dbg.p("cli main");
            doMain(rest);

            dbg.p("cli exit");
        } catch (ControlBreak b) {
            return;
        } finally {
            while (restArgs.size() > preRestSize)
                restArgs.remove(restArgs.size() - 1);
        }
    }

    /** Do nothing, to be overrided. */
    @OverrideOption(chain = ChainUsage.PREFERRED)
    void _postInit()
            throws Exception {
    }

    /** Do nothing, to be overrided. */
    protected void _boot()
            throws Exception {
    }

    /**
     * @throws ControlExit
     */
    protected void _exit()
            throws Exception {
        throw new ControlExit();
    }

    /**
     * return non-null value to enable the `<i>default file is read from stdin</i>' mode.
     * 
     * @return <code>null</code>
     */
    protected InputStream _getDefaultIn() {
        return null;
    }

    /**
     * User main method: the start point after all options are parsed.
     * <p>
     * <b>Default Implementation:</b> Get default input (usually the stdin) if no file is specified,
     * or show the help page if default input is disabled. Next step:
     * {@link #doMainManaged(String[])}
     */
    @OverrideOption(group = "basicMain")
    protected void doMain(String[] args)
            throws Exception {
        if (args.length == 0) {
            InputStream in = _getDefaultIn();
            if (in != null)
                doFileArgument(null, in);
            else
                _help();
        } else {
            doMainManaged(args);
        }
    }

    /**
     * 
     * User repeatable-main method. <i>({@link #_getDefaultIn()} is never used)</i>
     * <p>
     * <b>Default Implementation:</b> Expand each wild-card argument when necessary. Next step:
     * {@link #doFileArgument(File)}
     */
    @OverrideOption(group = "basicMain")
    protected void doMainManaged(String[] args)
            throws Exception {
        for (String arg : args) {
            File file = SystemColos.cwd.join(getClass(), arg);
            expandFileArgument(file);
        }
    }

    boolean enableWildcards = false;

    void expandFileArgument(File wildcards)
            throws Exception {
        String name = wildcards.getName();
        if (enableWildcards && (name.contains("*") || name.contains("?"))) {
// FileSystem fs = FileSystems.getDefault();
// PathMatcher pathMatcher = fs.getPathMatcher("glob:name");
// for (File sibling : wildcards.getParentFile().listFiles())
// if (pathMatcher.matches(sibling.toPath())) {
// L.debug("Wildcard expansion: ", wildcards, " -> ", sibling);
// doFileArgument(sibling);
// }
            return;
        }
        doFileArgument(wildcards);
    }

    /**
     * <b>Default Implementation:</b> Open the file and continue to
     * {@link #doFileArgument(File, InputStream)}
     * 
     * @param file
     *            canonical file
     */
    @OverrideOption(group = "basicMain")
    protected void doFileArgument(IFile file)
            throws Exception {
        assert file != null;
        InputStream in = file.getInputSource().newInputStream();
        try {
            doFileArgument(file, in);
        } finally {
            in.close();
        }
    }

    /**
     * If no argument specified, _main(null, stdin) is called.
     * <p>
     * <b>Default Implementation:</b> not implemented.
     * 
     * @param file
     *            canonical file or null
     */
    @OverrideOption(group = "basicMain")
    protected void doFileArgument(IFile file, InputStream in)
            throws Exception {
        throw new NotImplementedException();
    }

}
