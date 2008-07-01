package net.bodz.bas.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map.Entry;

import net.bodz.bas.cli.util.Author;
import net.bodz.bas.cli.util.Doc;
import net.bodz.bas.cli.util.Rcs;
import net.bodz.bas.cli.util.RcsKeywords;
import net.bodz.bas.cli.util.Version;
import net.bodz.bas.cli.util.VersionInfo;
import net.bodz.bas.functors.lang.ControlBreak;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.annotations.OverrideOption;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.err.UnexpectedException;
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
public class BasicCLI extends LogBase {

    protected VersionInfo _version;

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

        System.err.println(String.format("[%s] %s", _logPrefix, doc));
        System.err.println(String.format(
                "Written by %s,  Version %s,  Last updated at %s",
                _version.author, VersionInfo.getVersion(verjoin), _version
                        .getDate()));
        throw new ControlBreak();
    }

    @Option(alias = "h", doc = "show help info")
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
    protected <T extends BasicCLI> ClassOptions<T> getClassOptions() {
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

            if (_verbose >= 2) {
                for (Entry<String, _Option<?>> entry : opts.getOptions()
                        .entrySet()) {
                    _Option<?> opt = entry.getValue();
                    String optnam = opt.getCanonicalName();
                    if (!optnam.equals(entry.getKey()))
                        continue;
                    Object optval = opt.get(this);
                    if (optval instanceof CallInfo)
                        continue;
                    _log3(optnam, " = ", Util.dispval(optval));
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
