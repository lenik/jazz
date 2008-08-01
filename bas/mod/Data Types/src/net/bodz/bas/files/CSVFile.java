package net.bodz.bas.files;

import java.util.Iterator;

import net.bodz.bas.lang.err.NotImplementedException;

public class CSVFile extends _FileType implements FileSource<String[]> {

    public CSVFile() {
        super();
    }

    @Override
    public Iterator<String[]> iterator() {
        throw new NotImplementedException();
    }

}
