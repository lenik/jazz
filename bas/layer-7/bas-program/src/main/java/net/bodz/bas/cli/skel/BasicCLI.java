package net.bodz.bas.cli.skel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.java.nio.WildcardsExpander;
import net.bodz.bas.c.java.util.Iterables;
import net.bodz.bas.c.object.SimpleObjectFormatter;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.c.string.StringQuoted;
import net.bodz.bas.cli.model.HelpPageFormatter;
import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.cli.model.MethodCall;
import net.bodz.bas.cli.model.OptionGroupFactory;
import net.bodz.bas.cli.model.OptionGroupParseFlags;
import net.bodz.bas.cli.plugin.CLIPlugins;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.gui.dialog.ConsoleDialogs;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.ControlContinue;
import net.bodz.bas.lang.ControlExit;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.meta.build.ReleaseDescription;
import net.bodz.bas.meta.source.ChainUsage;
import net.bodz.bas.meta.source.OverrideOption;
import net.bodz.bas.model.IExecutableVarArgsX;
import net.bodz.bas.model.ITransformer;
import net.bodz.bas.potato.model.IType;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.ParserUtil;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.mda.xjdoc.conv.ClassDocLoadException;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model1.ArtifactDoc;

/**
 * Basic CLI Framework
 */
@RcsKeywords(id = "$Id$")
public abstract class BasicCLI
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

    protected IUserDialogs dialogs = ConsoleDialogs.stdout;

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
        out.printf("[%s] %s\n", artifactDoc.getLabel(), artifactDoc.getText().getHeadPar());
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
            throws CLISyntaxException {
        _help(Stdio.cerr);
        throw new ControlBreak();
    }

    protected void _help(IPrintOut out) {
        _version(out);
        out.println();

        HelpPageFormatter formatter = new HelpPageFormatter();
        formatter.setDescriptionColumn(29);

        String doc = formatter.format(getOptions());
        out.print(doc);

        if (plugins != null)
            plugins.help(out, "");

        out.flush();
    }

    private transient IOptionGroup classOptionGroup;

    public BasicCLI() {
        _vars = new HashMap<String, Object>();
    }

    public IType getPotatoType() {
        return Traits.getTrait(getClass(), IType.class);
    }

    protected ArtifactDoc getArtifactDoc() {
        return null;
    }

    public synchronized <T extends BasicCLI> IOptionGroup getOptions() {
        if (classOptionGroup == null) {
            Class<T> clazz = (Class<T>) this.getClass();
            classOptionGroup = OptionGroupFactory.getClassOptions(clazz);
        }
        return classOptionGroup;
    }

    public List<String> parseArguments(String... args)
            throws CLISyntaxException, ParseException {
        if (logger.isDebugEnabled())
            logger.debug("Parse arguments: " + StringArray.join(", ", args));

        IOptionGroup options = getOptions();
        List<String> rejected = options.parse(OptionGroupParseFlags.DEFAULT, this, args);
        return rejected;
    }

    public void runExtra(String cmdline)
            throws Throwable {
        String[] args = {};
        if (cmdline != null)
            args = StringQuoted.split(cmdline);
        execute(args);
    }

    @Override
    public void run() {
        try {
            execute(new String[0]);
        } catch (Exception e) {
            dialogs.alert(e.getMessage(), e);
        }
    }

    /**
     * Public access: so derivations don't have to declare static main()s.
     */
    @Override
    public synchronized void execute(String... args)
            throws Exception {
        parseArguments(args);

        try {
            List<String> remainingArgs = parseArguments(args);

            logger.debug("[CLI] Pre-boot");
            _postInit();
            _boot();

            if (logger.isDebugEnabled()) {
                for (Entry<String, IOption> entry : classOptionGroup.getLocalOptionMap().entrySet()) {
                    IOption option = entry.getValue();
                    String optionName = option.getName();
                    if (!optionName.equals(entry.getKey()))
                        continue;
                    Object optionValue = option.property().getValue(this);
                    if (optionValue instanceof MethodCall)
                        continue;
                    logger.debug(optionName, " = ", SimpleObjectFormatter.dispval(optionValue));
                }
                for (Entry<String, Object> entry : _vars.entrySet()) {
                    String name = entry.getKey();
                    Object value = entry.getValue();
                    logger.debug("var ", name, " = ", value);
                }
            }

            try {
                logger.debug("[CLI] Execute main method");
                mainImpl(remainingArgs.toArray(new String[0]));
            } catch (ControlContinue _cont) {
            }

            logger.debug("[CLI] Exit");
        } catch (ControlBreak _brk) { // implied ControlExit also.
            return;
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
     * User main method: the start point after all options are parsed.
     * <p>
     * <b>Default Implementation:</b> Get default input (usually the stdin) if no file is specified,
     * or show the help page if default input is disabled. Next step:
     * {@link #doMainManaged(String[])}
     */
    @OverrideOption(group = "basicMain")
    protected abstract void mainImpl(String... args)
            throws Exception;

    protected Iterable<IFile> expandFiles(final String... pathnames) {
        return Iterables.transform(expandWildcards(pathnames), _resolver);
    }

    private static final ITransformer<String, IFile> _resolver = new ITransformer<String, IFile>() {
        /** @throws FileResolveException */
        @Override
        public IFile transform(String pathname) {
            IFile file = VFS.resolve(pathname);
            return file;
        }
    };

    protected Iterable<String> expandWildcards(final String... pathnames) {
        List<Iterable<String>> iterables = new ArrayList<>(pathnames.length);
        for (int i = 0; i < pathnames.length; i++)
            iterables.set(i, expandWildcards(pathnames[i]));
        return Iterables.concat(iterables);
    }

    protected Iterable<String> expandWildcards(final String pathname) {
        WildcardsExpander expander = new WildcardsExpander(pathname);
        expander.logger = logger; // Utilize the verbose option.
        return expander;
    }

}
