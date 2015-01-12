package net.bodz.bas.c.java.nio;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.c.system.UserDirScr;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.iterator.Iterators;
import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.bas.t.iterator.StackedIterator;

public class WildcardsExpander
        implements Iterable<String> {

    static Logger _logger = LoggerFactory.getLogger(WildcardsExpander.class);
    public Logger logger = _logger;

    private boolean wildcardsEnabled = true;

    private String expr;
    private Path startPath;
    private boolean bareName;
    private PathMatcher matcher;

    public WildcardsExpander(String expr) {
        this(null, expr);
    }

    public WildcardsExpander(String start, String expr) {
        this.expr = expr;

        int quest = expr.indexOf('?');
        int aster = expr.indexOf('*');
        int anyPattern = quest == -1 || aster == -1 ? Math.max(quest, aster) : Math.min(quest, aster);
        if (anyPattern == -1) {
            setStartPath(start, expr);
            return;
        }

        if (!wildcardsEnabled)
            throw new IllegalArgumentException("Wildcards isn't supported: " + expr);

        int slash = expr.lastIndexOf('/', anyPattern);
        String knownDir;
        if (slash == -1) {
            bareName = true;
            // knownDir = ".";
            knownDir = UserDirScr.getInstance().get().toString();
            expr = knownDir + "/" + expr;
        } else {
            knownDir = expr.substring(0, slash);
        }
        setStartPath(start, knownDir);
        matcher = FileSystems.getDefault().getPathMatcher("glob:" + expr);
    }

    void setStartPath(String start, String spec) {
        FileSystem fileSystem = FileSystems.getDefault();

        if (start == null || spec.startsWith("/"))
            startPath = fileSystem.getPath(spec);
        else
            startPath = fileSystem.getPath(start, spec);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iter();
    }

    public List<String> list() {
        List<String> list = new ArrayList<String>();
        for (String item : this)
            list.add(item);
        return list;
    }

    class Iter
            extends PrefetchedIterator<String> {

        StackedIterator<Path> stack = new StackedIterator<>();

        public Iter() {
            Iterator<Path> startIterator = Iterators.iterate(startPath);
            stack.push(startIterator);
        }

        @Override
        protected String fetch() {
            if (!stack.hasNext())
                return end();
            Path path = stack.next();

            File file = path.toFile();
            if (file.isDirectory()) {
                File[] childFiles = file.listFiles();
                if (childFiles != null) {
                    List<Path> childPaths = new ArrayList<Path>(childFiles.length);
                    for (File childFile : childFiles)
                        childPaths.add(childFile.toPath());
                    stack.push(childPaths.iterator());
                }
            }

            if (matcher != null) {
                if (!matcher.matches(path))
                    return fetch();
            }

            Path relativePath = startPath.relativize(path);
            String name = relativePath.toString();
            if (!bareName) {
                if (name.isEmpty())
                    name = startPath.toString();
                else
                    name = startPath.toString() + "/" + name;
            }

            logger.debug("Wildcard expansion: ", expr, " -> ", name);
            return name;
        }

    }

}
