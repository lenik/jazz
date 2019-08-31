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

public class IncomingSaver
        implements IIncomingFileHandler {

    static final Logger logger = LoggerFactory.getLogger(IncomingSaver.class);

    Object context;
    String schema;
    ILazyId getId;
    IFileNameListener fileNameListener = IFileNameListener.NOP;

    public IncomingSaver(String schema, Object context, ILazyId getId) {
        if (schema == null)
            throw new NullPointerException("schema");
        if (context == null)
            throw new NullPointerException("context");
        if (getId == null)
            throw new NullPointerException("getId");
        this.schema = schema;
        this.context = context;
        this.getId = getId;
    }

    public IncomingSaver getId(ILazyId fn) {
        this.getId = fn;
        return this;
    }

    public IncomingSaver fileName(IFileNameListener fn) {
        this.fileNameListener = fn;
        return this;
    }

    protected String getName(ItemFile item) {
        String name = item.getName();
        String ext = FilePath.getExtension(name, true);
        return item.getSha1() + ext;
    }

    @Override
    public void accept(List<ItemFile> items) {
        if (items == null || items.isEmpty())
            return;

        IVirtualHost vhost = CurrentVirtualHost.getVirtualHost();
        File schemaDir = DefaultSiteDirs.getInstance().getUploadDir(vhost.getName(), schema);

        Iterator<ItemFile> iter = items.iterator();
        while (iter.hasNext()) {
            ItemFile item = iter.next();
            String newName = getName(item);
            Object id = getId.require(context);
            item.setDir(schema + "/" + id);

            File dst = new File(schemaDir, id + "/" + newName);
            if (dst.exists()) {// already submitted.
                item.setFile(dst);
                continue;
            }

            File tmp = new File(schemaDir, getTmpName(item));
            if (!tmp.exists()) {
                logger.error("Can't find the temporary uploaded file: " + tmp);
                iter.remove(); // Remove error items from the list.
                continue;
            }

            File dstParent = dst.getParentFile();
            if (!dstParent.exists())
                dstParent.mkdirs();

            boolean usePath = false;
            try {
                if (usePath) {
                    // XXX failed on chinese filenames.
                    // See: https://stackoverflow.com/questions/37409379/
                    // # The encoding can be 'ANSI_X3.4-1968' when not specified.
                    // : ${JAVAOPTS:=-ea -Dsun.jnu.encoding=utf-8 -Dfile.encoding=utf-8}
                    Path tmpPath = tmp.toPath();
                    Path dstPath = dst.toPath();
                    Files.move(tmpPath, dstPath, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    if (dst.exists() && !dst.delete())
                        logger.error("Failed to delete pre-existing file: " + dst);
                    if (!tmp.renameTo(dst))
                        logger.errorf("Failed to rename %s to %s: ", tmp, dst);
                }
                item.setFile(dst);
            } catch (IOException e) {
                // 缺失的图片会使用户自己主动去重新上传。
                logger.error("Failed to move", e);
                iter.remove();
            }

            if (fileNameListener != null)
                fileNameListener.onFileNameChange(tmp, dst);
        }
    }

    static String getTmpName(ItemFile item) {
        String name = item.getName();
        String dotExt = FilePath.getExtension(name, true);
        return "tmp/" + item.getSha1() + dotExt;
    }

}
