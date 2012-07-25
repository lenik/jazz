package net.bodz.bas.cli.help;

import java.util.Map;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.cli.skel.CLIException;
import net.bodz.bas.util.order.AbstractNonNullComparator;

public class HelpdocFormatter {

    int tabSize;
    int docColumn;
    boolean flatten;

    @SuppressWarnings("unchecked")
    public String helpOptions(IOptionGroup group)
            throws CLIException {
        Map<String, IOption> options = group.getOptions();
        StringBuilder buffer = new StringBuilder(options.size() * 80);

        AlignedOptionFormatter formatter = new AlignedOptionFormatter();
        formatter.setTabSize(tabSize);
        formatter.setDocColumn(docColumn);

        buffer.append("Syntax: \n");

        while (group != null) {
            group.getDisplayName();
            group.getOptions().values();
            buffer.append("Options for " + Strings.ucfirst(name) + ":\n");
            buffer.append(formatter.format(group, selection));
        }
        return buffer.toString();
    }
}

class OptionNameComparator
        extends AbstractNonNullComparator<IOption> {

    @Override
    public int compareNonNull(IOption o1, IOption o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();

        int cmp = name1.compareTo(name2);
        if (cmp != 0)
            return cmp;

        return compareFallback(o1, o2);
    }

    static final OptionNameComparator INSTANCE = new OptionNameComparator();

}