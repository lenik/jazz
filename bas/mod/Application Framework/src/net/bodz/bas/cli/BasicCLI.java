package net.bodz.bas.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

import net.bodz.bas.annotations.ClassInfo;
import net.bodz.bas.cli.ext.CLIPlugin;
import net.bodz.bas.cli.ext.CLIPlugins;
import net.bodz.bas.cli.util.Rcs;
import net.bodz.bas.cli.util.RcsKeywords;
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
import net.bodz.bas.mod.CreateException;
import net.bodz.bas.mod.plugins.PluginClass;
import net.bodz.bas.mod.plugins.PluginException;
import net.bodz.bas.types.AutoTypeMap;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.TypeParsers.ALogParser;
import net.bodz.bas.types.TypeParsers.CharOutParser;
import net.bodz.bas.types.util.Strings;

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

    protected AutoTypeMap<String, Object> _vars;

    @Option(name = "define", alias = ".D", vnam = "NAM=VAL", doc = "define variables")
    protected void _define(String exp) throws ParseException {
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
            TypeParser<?> parser = TypeParsers.guess(type);
            val = parser.parse(exp);
        }
        _vars.put(nam, val);
    }

    protected Class<?> _getVarType(String name) {
        return String.class;
    }

    protected CLIPlugins plugins = new CLIPlugins();

    private class PluginParser<T extends CLIPlugin> implements TypeParser<T> {

        @Override
        public boolean variant() {
            return true;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T parse(String idCtor) throws ParseException {
            int eq = idCtor.indexOf('=');
            String id = idCtor;
            String arg = null;
            if (eq != -1) {
                id = idCtor.substring(0, eq);
                arg = idCtor.substring(eq + 1);
            }
            try {
                PluginClass<?> pluginClass = plugins.getPluginClass(
                        CLIPlugin.class, id);
                Class<?> clazz = pluginClass.getType();
                Constructor<?>[] ctors = clazz.getConstructors();
                if (ctors.length != 1) {
                    // fail("too many ctors to choice");
                    return (T) pluginClass.newInstance(arg);
                }

                Class<?>[] sig = ctors[0].getParameterTypes();
                int len = sig.length;
                int off = 0;
                if (clazz.isMemberClass()) {
                    len--;
                    off++;
                }
                if (len == 0) {
                    assert arg == null;
                    return (T) pluginClass.newInstance(arg);
                }
                if (len != 1)
                    throw new PluginException(
                            "no suitable constructor to use: " + pluginClass);

                Class<?> sig0 = sig[off];
                if (sig0 == String.class)
                    return (T) pluginClass.newInstance(arg);
                if (!sig0.isArray()) {
                    TypeParser<?> parser = TypeParsers.guess(sig0);
                    Object val = parser.parse(arg);
                    return (T) pluginClass.newInstance(val);
                }

                // ctor(E[] array)
                String[] args = {};
                if (arg != null)
                    args = arg.split(",");
                Class<?> valtype = sig0.getComponentType();
                if (valtype == String.class)
                    return (T) pluginClass.newInstance((Object) args);

                TypeParser<?> parser = TypeParsers.guess(valtype);
                Object valarray = Array.newInstance(valtype, args.length);
                for (int i = 0; i < args.length; i++) {
                    Object val = parser.parse(args[i]);
                    Array.set(valarray, i, val);
                }
                return (T) pluginClass.newInstance((Object) valarray);
            } catch (PluginException e) {
                throw new ParseException(e.getMessage(), e);
            } catch (CreateException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }
    }

    protected VersionInfo _version;

    protected class CLILog extends ALog {

        public CLILog(int level) {
            super(level);
            HashMap<String, Object> hmap = new HashMap<String, Object>();
            _vars = new AutoTypeMap<String, Object>(hmap);
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
        TypeParsers.register(CLIPlugin.class, new PluginParser<CLIPlugin>());
        L = new CLILog(L.getLevel());
    }

    @Option(doc = "show version info")
    protected void _version(CharOut out) {
        Class<? extends BasicCLI> clazz = getClass();
        ClassInfo info = ClassInfo.get(clazz);

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

        int[] majorver = info.getVersion();
        if (majorver == null)
            majorver = new int[] { 0 };
        int[] verjoin = new int[majorver.length + _version.revision.length];
        System.arraycopy(majorver, 0, verjoin, 0, majorver.length);
        System.arraycopy(_version.revision, 0, verjoin, majorver.length,
                _version.revision.length);

        String author = info.getAuthor();
        if (author != null)
            _version.author = author;
        if (_version.author == null)
            _version.author = "Caynoh";
        if (_version.state == null)
            _version.state = "UNKNOWN";

        String doc = info.getDoc();
        if (doc == null)
            doc = clazz.getName();

        out.printf("[%s] %s\n", name, doc);
        out.printf("Written by %s,  Version %s,  Last updated at %s\n",
                _version.author, //
                Strings.joinDot(verjoin), _version.getDate());
        throw new ControlBreak();
    }

    @Option(alias = ".h", doc = "show help info")
    protected final void _help() throws CLIException {
        _help(CharOuts.stderr);
    }

    protected void _help(CharOut out) throws CLIException {
        try {
            _version(out);
        } catch (ControlBreak b) {
        }
        out.println();
        String hlp_opts = ClassCLI.helpOptions(getClass(), 4, 29);
        out.print(hlp_opts);

        if (plugins != null)
            plugins.help(out, "");
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
                for (Entry<String, Object> entry : _vars.entrySet()) {
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

    protected InputStream _getDefaultIn() {
        return System.in;
    }

    @OverrideOption(group = "basicMain")
    protected void _main(String[] args) throws Throwable {
        if (args.length == 0) {
            InputStream in = _getDefaultIn();
            if (in != null)
                _main(null, in);
            else
                _help();
        } else
            for (String arg : args)
                _main(new File(arg));
    }

    /** if no argument specified, _main(null) is called. */
    @OverrideOption(group = "basicMain")
    protected void _main(File file) throws Throwable {
        assert file != null;
        FileInputStream in = new FileInputStream(file);
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
