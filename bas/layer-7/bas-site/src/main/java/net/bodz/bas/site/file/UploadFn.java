package net.bodz.bas.site.file;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.source.FnHelper;
import net.bodz.lily.entity.ILazyId;

@FnHelper
public class UploadFn {

    static final Logger logger = LoggerFactory.getLogger(UploadFn.class);

    /**
     * @see IncomingSaver
     */
    @Deprecated
    public static void submitFiles(Object context, List<ItemFile> items, String schema, ILazyId idFn,
            IFileNameListener listener)
            throws IOException {
        IncomingSaver handler = new IncomingSaver(schema, context, idFn);
        handler.accept(items);
    }

}
