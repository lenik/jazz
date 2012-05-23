package net.bodz.bas.cli;

import java.util.Collection;

import net.bodz.bas.cli.opt.IOption;

public interface IOptionFormatter {

    String format(Collection<IOption> options);

}
