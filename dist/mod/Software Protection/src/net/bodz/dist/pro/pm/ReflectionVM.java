package net.bodz.dist.pro.pm;

import net.bodz.bas.lang.err.NotImplementedException;

public class ReflectionVM extends VirtualMachine {

    static final int SET_FIELD  = 1;
    static final int WRITE_FILE = 2;

    @Override
    protected void execute(int opcode, Object parameter) throws VMException {
        throw new NotImplementedException();
    }

}
