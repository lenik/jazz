package net.bodz.xfo.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.bodz.xfo.types.AttributedFile;
import net.bodz.xfo.types.ComplexFile;

public class AllPossibleAttributedFile extends ComplexFile {

    public AllPossibleAttributedFile(File file) {
        super(findPossibleAttributedFiles(file));
    }

    public static AttributedFile[] findPossibleAttributedFiles(File file) {
        if (file == null)
            throw new NullPointerException("file");
        List<AttributedFile> list = new ArrayList<AttributedFile>();
        return list.toArray(new AttributedFile[0]);
    }

}
