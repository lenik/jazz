package net.bodz.bas.cli.help;

import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.cli.model.IOptionGroup;

public interface IOptionFormatter {

    String format(IOptionGroup optionGroup, Iterable<IOption> selection);

}
