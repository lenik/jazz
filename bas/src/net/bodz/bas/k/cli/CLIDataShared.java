package net.bodz.bas.k.cli;

import java.util.List;

public class CLIDataShared extends CLIData {

    public CLIDataShared(List<String> arguments, boolean copy) {
        super(arguments, copy);
    }

    public CLIDataShared(List<String> arguments) {
        super(arguments);
    }

    public CLIDataShared(String[] arguments) {
        super(arguments);
    }

    @Override
    protected String remove(int index) {
        return arguments.get(index);
    }

}
