package net.bodz.bas.cli.skel;

import java.io.File;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.rmi.CORBA.Util;
import javax.script.ScriptException;

import org.apache.commons.lang.ArrayUtils;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.cli.ClassOptions;
import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.cli.model.MethodCall;
import net.bodz.bas.cli.plugin.CLIPlugins;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.gui.ia.ConsoleInteraction;
import net.bodz.bas.gui.ia.IUserInteraction;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.ControlExit;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.meta.build.ReleaseDescription;
import net.bodz.bas.meta.codehint.ChainUsage;
import net.bodz.bas.meta.codehint.OverrideOption;
import net.bodz.bas.model.IExecutableVarArgsX;
import net.bodz.bas.potato.mda.tagbook.ArtifactDoc;
import net.bodz.bas.potato.model.IType;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.ParserUtil;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.SystemColos;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;
import net.bodz.mda.xjdoc.conv.ClassDocLoadException;
import net.bodz.mda.xjdoc.conv.ClassDocs;

/**
 * Basic CLI Framework
 */
@RcsKeywords(id = "$Id$")
public class BasicCLI
        implements Runnable, IExecutableVarArgsX<String, Exception>, II18nCapable {

    /**
     * @option --stdout hidden weak
     */
    protected IPrintOut _stdout = Stdio.cout;

    /**
     * @option --logger hidden
     */
    protected Logger logger = LoggerFactory.getLogger(BasicCLI.class);
    // LogTerms.resolveFile(1);

    protected IUserInteraction ia = ConsoleInteraction.stdout;

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
        logger.setDelta(logger.getDelta() + 1);
    }

    /**
     * Repeat to get less info.
     * 
     * @option -q
     */
    void _quiet() {
        logger.setDelta(logger.getDelta() - 1);
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
        ArtifactDoc artifactDoc;
        try {
            artifactDoc = ClassDocs.loadFromResource(getClass()).as(ArtifactDoc.class);
        } catch (ClassDocLoadException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        ReleaseDescription release = artifactDoc.getReleaseDescription();
        out.printf("[%s] %s\n", artifactDoc.getLabel(), artifactDoc.getTextHeader());
        out.printf("Written by %s,  Version %s,  Last updated at %s\n", //
                artifactDoc.getAuthor(), //
                artifactDoc.getVersion(), //
                release.getReleaseDateString());
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

    private transient IOptionGroup classOptionGroup;
    private List<String> remainArguments = new ArrayList<String>();

    public BasicCLI() {
        _vars = new HashMap<String, Object>();
    }

    public IType getPotatoType()
            throws ScriptException {
        return Traits.getTrait(getClass(), IType.class);
    }

    public synchronized <T extends BasicCLI> IOptionGroup getOptions()
            throws CLIException {
        if (classOptionGroup == null) {
            Class<T> clazz = (Class<T>) this.getClass();
            classOptionGroup = ClassOptions.getClassOptions(clazz);
        }
        return classOptionGroup;
    }

    public void parseArguments(String... args)
            throws CLIException, ParseException {
        if (logger.isDebugEnabled())
            logger.debug("Parse arguments: " + StringArray.join(", ", args));

        IOptionGroup options = getOptions();
        String[] rejected = options.load(this, args);

        for (String arg : rejected)
            remainArguments.add(arg);
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
            ia.alert(e.getMessage(), e);
        }
    }

    /**
     * Public access: so derivations don't have to declare static main()s.
     */
    @Override
    public synchronized void execute(String... args)
            throws Exception {
        parseArguments(args);

        int preRestSize = remainArguments.size(); // make climain() reentrant.

        try {
            parseArguments(args);

            logger.debug("[CLI] Pre-boot");
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

            if (logger.isDebugEnabled()) {
                for (Entry<String, IOption> entry : classOptionGroup.getLocalOptionMap().entrySet()) {
                    IOption option = entry.getValue();
                    String optionName = option.getName();
                    if (!optionName.equals(entry.getKey()))
                        continue;
                    Object optionValue = option.parse(this);
                    if (optionValue instanceof MethodCall)
                        continue;
                    logger.debug(optionName, " = ", Util.dispval(optionValue));
                }
                for (Entry<String, Object> entry : _vars.entrySet()) {
                    String name = entry.getKey();
                    Object value = entry.getValue();
                    logger.debug("var ", name, " = ", value);
                }
            }
            String[] rest = remainArguments.toArray(ArrayUtils.EMPTY_STRING_ARRAY);

            logger.debug("[CLI] Execute main method");
            doMain(rest);

            logger.debug("[CLI] Exit");
        } catch (ControlBreak b) {
            return;
        } finally {
            while (remainArguments.size() > preRestSize)
                remainArguments.remove(remainArguments.size() - 1);
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
            FileSystem fs = FileSystems.getDefault();
            PathMatcher pathMatcher = fs.getPathMatcher("glob:name");
            for (File sibling : wildcards.getParentFile().listFiles())
                if (pathMatcher.matches(sibling.toPath())) {
                    logger.debug("Wildcard expansion: ", wildcards, " -> ", sibling);
                    IFile _sibling = new JavaioFile(sibling);
                    doFileArgument(_sibling);
                }
            return;
        }
        IFile plainFile = new JavaioFile(wildcards);
        doFileArgument(plainFile);
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

    protected ArtifactDoc getArtifactDoc() {
        return null;
    }

}
