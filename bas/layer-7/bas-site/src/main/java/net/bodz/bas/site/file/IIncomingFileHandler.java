package net.bodz.bas.site.file;

import java.io.IOException;
import java.util.List;

public interface IIncomingFileHandler {

    void accept(List<ItemFile> items)
            throws IOException;

}
