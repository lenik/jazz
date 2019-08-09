package net.bodz.bas.site.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.site.DefaultSiteDirs;
import net.bodz.bas.site.vhost.CurrentVirtualHost;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.lily.entity.ILazyId;

public class UploadFn {

    static final Logger logger = LoggerFactory.getLogger(UploadFn.class);

    public static void submitFiles(Object context, List<ItemFile> items, String schema, ILazyId idFn,
            IFileNameListener listener) {
        if (items == null || items.isEmpty())
            return;

        IVirtualHost vhost = CurrentVirtualHost.getVirtualHost();
        File lifeDir = DefaultSiteDirs.getInstance().getUploadDir(vhost.getName(), schema);

        Iterator<ItemFile> iter = items.iterator();
        while (iter.hasNext()) {
            ItemFile item = iter.next();
            String newName = item.getName(); // TODO use sha1?
            Object id = idFn.require(context);
            item.setDir(schema + "/" + id);

            File dst = new File(lifeDir, id + "/" + newName);
            if (dst.exists()) // already submitted.
                continue;

            File tmp = new File(lifeDir, getTmpName(item));
            if (!tmp.exists()) {
                logger.error("Can't find the temporary uploaded file: " + tmp);
                iter.remove(); // Remove error items from the list.
                continue;
            }

            File dstDir = dst.getParentFile();
            if (!dstDir.exists())
                dstDir.mkdirs();

            boolean usePath = false;
            try {
                // XXX failed on chinese filenames.
                // See: https://stackoverflow.com/questions/37409379/
                // # The encoding can be 'ANSI_X3.4-1968' when not specified.
                // : ${JAVAOPTS:=-ea -Dsun.jnu.encoding=utf-8 -Dfile.encoding=utf-8}
                if (usePath) {
                    Path tmpPath = tmp.toPath();
                    Path dstPath = dst.toPath();
                    Files.move(tmpPath, dstPath, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    if (dst.exists() && !dst.delete())
                        logger.error("Failed to delete pre-existing file: " + dst);
                    if (!tmp.renameTo(dst))
                        logger.errorf("Failed to rename %s to %s: ", tmp, dst);
                }
            } catch (IOException e) {
                // 缺失的图片会使用户自己主动去重新上传。
                logger.error("Failed to move", e);
                iter.remove();
            }

            listener.onFileNameChange(tmp, dst);
        }
    }

    static String getTmpName(ItemFile item) {
        String name = item.getName();
        String dotExt = FilePath.getExtension(name, true);
        return "tmp/" + item.getSha1() + dotExt;
    }

}
