package net.bodz.bas.program.skel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.java.nio.WildcardsExpander;
import net.bodz.bas.c.object.SimpleObjectFormatter;
import net.bodz.bas.c.string.StringQuoted;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.control.ControlBreak;
import net.bodz.bas.err.control.ControlContinue;
import net.bodz.bas.err.control.ControlExit;
import net.bodz.bas.fn.ITransformer;
import net.bodz.bas.gui.dialog.ConsoleDialogs;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.meta.build.ReleaseDescription;
import net.bodz.bas.meta.source.OverrideOption;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.program.IProgram;
import net.bodz.bas.program.model.ArtifactObjectWithOptions;
import net.bodz.bas.program.model.HelpPageFormatter;
import net.bodz.bas.program.model.IOption;
import net.bodz.bas.program.model.MethodCall;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.ParserUtil;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.mda.xjdoc.model2.ArtifactDoc;

/**
 * Basic CLI Framework
 */
@RcsKeywords(id = "$Id$")
public abstract class BasicCLI
        extends ArtifactObjectWithOptions
        implements IProgram, Runnable, II18nCapable {

    private static final Logger logger = LoggerFactory.getLogger(BasicCLI.class);

    /**
     * @option --stdout hidden weak
     */
    protected IPrintOut _stdout = Stdio.cout;

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

    protected Map<String, Object> variableMap;

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
        variableMap.put(nam, val);
    }

    protected Class<?> _getVarType(String name) {
        return String.class;
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
        ArtifactDoc artifactDoc = getXjdoc();
        ReleaseDescription release = artifactDoc.getReleaseDescription();
        out.printf("[%s] %s\n", artifactDoc.getDisplayName(), artifactDoc.getText().getHeadPar());
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

        String doc = formatter.format(this);
        out.print(doc);

        out.flush();
    }

    public BasicCLI() {
        variableMap = new HashMap<String, Object>();
    }

    public IType getPotatoType() {
        return Traits.getTrait(getClass(), IType.class);
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

        logger.debug("Receive args from cmdline");
        List<String> remaining = receive(args);
        args = remaining.toArray(new String[0]);

        logger.debug("Reconfigure...");
        _reconfigure();
        reconfigure();
        logger.debug("Reconfigure done.");

        if (logger.isDebugEnabled()) {
            for (Entry<String, IOption> entry : getLocalOptionMap().entrySet()) {
                IOption option = entry.getValue();
                String optionName = option.getName();
                if (!optionName.equals(entry.getKey()))
                    continue;
                Object optionValue = option.property().getValue(this);
                if (optionValue instanceof MethodCall)
                    continue;
                logger.debug(optionName, " = ", SimpleObjectFormatter.dispval(optionValue));
            }
            for (Entry<String, Object> entry : variableMap.entrySet()) {
                String name = entry.getKey();
                Object value = entry.getValue();
                logger.debug("var ", name, " = ", value);
            }
        }

        while (true)
            try {
                logger.debug("Program Begin");
                mainImpl(args);
                logger.debug("Program End");
                break;
            } catch (ControlExit c) {
                break;
            } catch (ControlContinue c) {
                continue;
            }
    }

    protected void _reconfigure()
            throws Exception {
    }

    protected void reconfigure()
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

    // Helper Methods

    protected Iterable<File> expandPojfFiles(final String... pathnames) {
        return Iterables.transform(expandWildcards(pathnames), pojfFileResolver);
    }

    protected Iterable<IFile> expandFiles(final String... pathnames) {
        return Iterables.transform(expandWildcards(pathnames), vfsFileResolver);
    }

    private static final ITransformer<String, File> pojfFileResolver = new ITransformer<String, File>() {
        /** @throws FileResolveException */
        @Override
        public File transform(String pathname) {
            File file = new File(pathname);
            return file;
        }
    };

    private static final ITransformer<String, IFile> vfsFileResolver = new ITransformer<String, IFile>() {
        /** @throws FileResolveException */
        @Override
        public IFile transform(String pathname) {
            IFile file = VFS.resolve(pathname);
            return file;
        }
    };

    protected Iterable<String> expandWildcards(final String... pathnames) {
        List<Iterable<String>> iterableList = new ArrayList<>(pathnames.length);
        for (String pathname : pathnames)
            iterableList.add(expandWildcards(pathname));
        return Iterables.concat(iterableList);
    }

    protected Iterable<String> expandWildcards(final String pathname) {
        WildcardsExpander expander = new WildcardsExpander(pathname);
        expander.logger = logger; // Utilize the verbose option.
        return expander;
    }

}
