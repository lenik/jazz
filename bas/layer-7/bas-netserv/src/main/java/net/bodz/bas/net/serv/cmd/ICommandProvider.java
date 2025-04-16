package net.bodz.bas.net.serv.cmd;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.cli.Command;

public interface ICommandProvider {

    boolean execute(Command cmd)
            throws IOException, ParseException;

}
