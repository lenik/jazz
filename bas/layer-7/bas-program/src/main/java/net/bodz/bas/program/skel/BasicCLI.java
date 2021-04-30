package net.bodz.bas.program.skel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.java.nio.WildcardsExpander;
import net.bodz.bas.c.string.StringQuoted;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fn.AbstractTransformer;
import net.bodz.bas.fn.ITransformer;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.program.IPerformanceAware;
import net.bodz.bas.program.model.AbstractProgram;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.ParserUtil;
import net.bodz.bas.ui.dialog.ConsoleDialogs;
import net.bodz.bas.ui.dialog.IUserDialogs;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;

/**
 * Basic CLI Framework
 *
 * <p lang="zh-cn">
 * 基本 CLI 框架
 *
 * @usage.std [OPTIONS] -- FILES...
 */
@RcsKeywords(id = "$Id$")
public abstract class BasicCLI
        extends AbstractProgram
        implements
            Runnable,
            II18nCapable,
            IPerformanceAware {

    private static final Logger logger = LoggerFactory.getLogger(BasicCLI.class);

    /**
     * Send output to the stdout.
     *
     * <p lang="zh-cn">
     * 将输出重定向到标准输出。
     *
     * @option --stdout hidden weak
     */
    protected IPrintOut stdout = Stdio.cout;

    protected IUserDialogs dialogs = ConsoleDialogs.stdout;

    /**
     * Show prefix in the log messages.
     *
     * <p lang="zh-cn">
     * 在输出的日志中显示信息源前缀。
     *
     * @option hidden
     */
    boolean _logWithPrefix = true;

    /**
     * Show date time in the log messages.
     *
     * <p lang="zh-cn">
     * 在输出的日志中包含日期信息。
     *
     * @option hidden
     */
    boolean _logWithDate = false;

    /**
     * Repeat to get more info.
     *
     * <p lang="zh-cn">
     * 重复此选项以显示更多可用的信息。
     *
     * @option -v
     */
    void _verbose() {
        logger.setDelta(logger.getDelta() + 1);
    }

    /**
     * Repeat to get less info.
     *
     * <p lang="zh-cn">
     * 重复此选项以抑制不必要的信息。
     *
     * @option -q
     */
    void _quiet() {
        logger.setDelta(logger.getDelta() - 1);
    }

    /**
     * Define variable.
     *
     * <p lang="zh-cn">
     * 定义变量。
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

    public Map<String, Object> getVariableMap() {
        return variableMap;
    }

    public IType getPotatoType() {
        return Typers.getTyper(getClass(), IType.class);
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

    // Helper Methods

    protected Iterable<File> expandPojfFiles(final String... pathnames) {
        return Iterables.transform(expandWildcards(pathnames), pojfFileResolver);
    }

    protected Iterable<IFile> expandFiles(final String... pathnames) {
        return Iterables.transform(expandWildcards(pathnames), vfsFileResolver);
    }

    private static final ITransformer<String, File> pojfFileResolver = new AbstractTransformer<String, File>() {
        /** @throws FileResolveException */
        @Override
        public File transform(String pathname) {
            File file = new File(pathname);
            return file;
        }
    };

    private static final ITransformer<String, IFile> vfsFileResolver = new AbstractTransformer<String, IFile>() {
        /** @throws FileResolveException */
        @Override
        public IFile transform(String pathname) {
            IFile file = VFS.resolve(pathname);
            return file;
        }
    };

    protected Iterable<String> expandWildcards(final String... pathnames) {
        List<Iterable<String>> iterableList = new ArrayList<Iterable<String>>(pathnames.length);
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
