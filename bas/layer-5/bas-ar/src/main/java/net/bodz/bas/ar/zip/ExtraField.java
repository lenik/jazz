package net.bodz.bas.ar.zip;

import java.io.IOException;

import net.bodz.bas.fmt.rst.RstDataStruct;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public abstract class ExtraField
        extends RstDataStruct {

    private static final long serialVersionUID = 1L;

    public short tag;
    public short size;

    @Override
    public int size() {
        return 4 + _size();
    }

    @Override
    public void readObject(IDataIn in)
            throws IOException {
        tag = in.readWord();
        size = in.readWord();
        _readObject(in);
    }

    @Override
    public void writeObject(IDataOut out)
            throws IOException {
        out.writeWord(tag);
        out.writeWord(size);
        _writeObject(out);
    }

    protected int _size() {
        return 0;
    }

    protected abstract void _readObject(IDataIn in)
            throws IOException;

    protected abstract void _writeObject(IDataOut out)
            throws IOException;

}
