package net.bodz.bas.cli.skel;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.util.iter.PrefetchedIterator;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.context.VFSColos;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;

public class WildcardExpander
        extends PrefetchedIterator<IFile> {

    Logger logger = LoggerFactory.getLogger(WildcardExpander.class);

    boolean wildcardsEnabled = false;
    List<IFile> files = new ArrayList<IFile>();
    int index = 0;

    public WildcardExpander(String pathname) {
        IFile workdir = VFSColos.workdir.get();

        String dirName = FilePath.getDirName(pathname);
        String baseName = FilePath.getBaseName(pathname);

        IFile dir = workdir.getChild(dirName);

        if (baseName.contains("*") || baseName.contains("?")) {
            if (!wildcardsEnabled)
                throw new IllegalArgumentException("Wildcards isn't supported: " + pathname);

            if (!(dir instanceof JavaioFile))
                throw new UnsupportedOperationException("Wildcards is only supported in local filesystem");
            File _dir = ((JavaioFile) dir).getLocalFile();

            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + baseName);
            for (File _sibling : _dir.getParentFile().listFiles())
                if (pathMatcher.matches(_sibling.toPath())) {
                    logger.debug("Wildcard expansion: ", _dir, " -> ", _sibling);
                    IFile sibling = new JavaioFile(_sibling);
                    files.add(sibling);
                }
        } else {
            files.add(dir.getChild(baseName));
        }
    }

    @Override
    protected IFile fetch() {
        if (index < files.size())
            return files.get(index++);
        else
            return end();
    }

}
