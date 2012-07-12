package net.bodz.bas.cli.help;

import java.util.Collection;

import net.bodz.bas.cli.model.IOption;

public interface IOptionFormatter {

    String format(Collection<IOption> options);

}
