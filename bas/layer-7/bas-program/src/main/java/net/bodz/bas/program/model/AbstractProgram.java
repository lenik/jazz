package net.bodz.bas.program.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.object.SimpleObjectFormatter;
import net.bodz.bas.c.system.System2;
import net.bodz.bas.err.control.ControlBreak;
import net.bodz.bas.err.control.ControlContinue;
import net.bodz.bas.err.control.ControlExit;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ReleaseDescription;
import net.bodz.bas.meta.source.OverrideOption;
import net.bodz.bas.potato.invoke.Invocation;
import net.bodz.bas.program.IProgram;
import net.bodz.bas.program.skel.CLISyntaxException;
import net.bodz.mda.xjdoc.model.artifact.ArtifactDoc;
import net.bodz.mda.xjdoc.model.artifact.ArtifactObject;

/**
 * Common Programs
 */
public abstract class AbstractProgram
        extends ArtifactObject
        implements
            IProgram,
            II18nCapable {

    static final Logger logger = LoggerFactory.getLogger(AbstractProgram.class);

    protected Map<String, Object> variableMap = new LinkedHashMap<String, Object>();

    private transient IOptionGroup optionGroup;

    @Override
    public synchronized IOptionGroup getOptionModel() {
        if (optionGroup == null)
            optionGroup = OptionGroupFactory.getClassOptions(getClass());
        return optionGroup;
    }

    public List<String> receive(String... args)
            throws CLISyntaxException, ApplyOptionException {
        List<String> rejected = getOptionModel().receive(this, args, OptionGroupParseFlags.DEFAULT);
        return rejected;
    }

    /**
     * Show version information
     *
     * <p lang="zh-cn">
     * 显示版本信息。
     *
     * @option --version
     */
    protected final void showVersion() {
        showVersion(Stdio.cout);
        throw new ControlBreak();
    }

    protected void showVersion(IPrintOut out) {
        ArtifactDoc artifactDoc = getXjdoc();
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
     * <p lang="zh-cn">
     * 显示这个帮助文本。
     *
     * @option -h --help weak
     */
    protected final void showHelpPage() {
        showHelpPage(Stdio.cout);
        throw new ControlBreak();
    }

    protected void showHelpPage(IPrintOut out) {
        showVersion(out);
        out.println();

        HelpPageFormatter formatter = new HelpPageFormatter();
        formatter.setDescriptionColumn(29);

        IOptionGroup optionModel = getOptionModel();
        if (optionModel == null)
            throw new NullPointerException("optionModel");
        String doc = formatter.format(optionModel);
        out.print(doc);

        out.flush();
    }

    /**
     * Public access: so derivations don't have to declare static main()s.
     */
    @Override
    public synchronized void execute(String... args)
            throws Exception {

        logger.debug("Parse cmdline arguments...");
        try {
            List<String> remaining = receive(args);
            args = remaining.toArray(new String[0]);
        } catch (ControlExit c) {
            System2.setExitStatus(c.getStatus());
            return;
        } catch (ControlBreak c) {
            return;
        } catch (CLISyntaxException e) {
            logger.error(e, "Illegal syntax: " + e.getMessage());
            System2.setExitStatus(1);
            return;
        }

        _reconfigure();
        reconfigure();

        IOptionGroup options = getOptionModel();
        if (logger.isDebugEnabled()) {
            for (Entry<String, IOption> entry : options.getLocalOptionMap().entrySet()) {
                IOption option = entry.getValue();
                String optionName = option.getName();
                if (!optionName.equals(entry.getKey()))
                    continue;
                Object optionValue = option.property().getValue(this);
                if (optionValue instanceof Invocation)
                    continue;
                logger.debug(optionName, " = ", SimpleObjectFormatter.dispval(optionValue));
            }
            for (Entry<String, Object> entry : variableMap.entrySet()) {
                String name = entry.getKey();
                Object value = entry.getValue();
                logger.debug("var ", name, " = ", value);
            }
        }

        try {
            while (true)
                try {
                    logger.debug("Program Begin");
                    mainImpl(args);
                    logger.debug("Program End");
                    break;
                } catch (ControlExit c) {
                    System2.setExitStatus(c.getStatus());
                    return;
                } catch (ControlContinue c) {
                    continue;
                }
        } finally {
            shutDown();
        }

        System2.setExitStatus(0);
        return;
    }

    protected void _reconfigure()
            throws Exception {
    }

    protected void reconfigure()
            throws Exception {
    }

    protected void shutDown()
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

}
