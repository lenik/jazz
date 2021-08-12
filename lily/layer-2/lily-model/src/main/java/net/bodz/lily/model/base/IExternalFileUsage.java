package net.bodz.lily.model.base;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.site.file.ItemFile;

public interface IExternalFileUsage {

    List<ItemFile> getExternalFiles();

    class fn {
        @SafeVarargs
        public static List<ItemFile> join(List<ItemFile>... lists) {
            List<ItemFile> files = new ArrayList<>();
            for (List<ItemFile> list : lists)
                if (list != null)
                    files.addAll(list);
            return files;
        }
    }

}
