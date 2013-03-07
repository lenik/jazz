package net.bodz.bas.program.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.string.StringLengthComparator;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class HelpPageFormatter {

    private boolean flatten;

    private int columns; // Get from terminfo?
    private int tabSize;
    private int descriptionColumn;
    private char[] indentChars;

    public HelpPageFormatter() {
        setColumns(80); // Get from terminfo?
        setTabSize(4);
        setDescriptionColumn(20);
    }

    public boolean isFlatten() {
        return flatten;
    }

    public void setFlatten(boolean flatten) {
        this.flatten = flatten;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getTabSize() {
        return tabSize;
    }

    public void setTabSize(int tabSize) {
        indentChars = new char[tabSize];
        Arrays.fill(indentChars, ' ');
    }

    public int getDescriptionColumn() {
        return descriptionColumn;
    }

    public void setDescriptionColumn(int descriptionColumn) {
        this.descriptionColumn = descriptionColumn;
    }

    public String format(IOptionGroup group) {
        StringBuilder buffer = new StringBuilder(100 * columns);

        Set<String> usageIds = new LinkedHashSet<String>();
        group.fillUsageIds(usageIds);
        buffer.append("Syntax: \n");
        for (String usageId : usageIds) {
            SyntaxUsage usage = group.getUsage(usageId);
            String syntax = usage.getSyntax();
            iString _description = usage.getDescription();
            String description = _description.toString();

            // substitute vars in syntax string...
            buffer.append(indentChars);
            buffer.append(syntax);

            if (!description.isEmpty()) {
                buffer.append(" - ");
                buffer.append(description);
            }

            buffer.append('\n');
        }

        Set<String> enabledKeys = new TreeSet<String>(StringLengthComparator.INSTANCE);
        StringBuilder line = new StringBuilder(columns);

        while (group != null) {

            Map<String, IOption> localOptionMap = group.getLocalOptionMap();
            List<IOption> localOptions = new ArrayList<>(localOptionMap.size());
            for (IOption option : localOptionMap.values()) {
                if (option.isHidden())
                    continue;
                localOptions.add(option);
            }

            if (!localOptions.isEmpty()) {
                iString groupDescription = group.getDescription();
                String groupHeader = "Options for " + Strings.ucfirstWords(groupDescription.toString());
                buffer.append("\n" + groupHeader + "\n");
                buffer.append(Strings.repeat(groupHeader.length(), '=') + "\n");
            }

            for (IOption option : localOptions) {
                line.setLength(0);
                line.append(indentChars);
                int offset = line.length();

                enabledKeys.clear();
                group.fillEnabledKeys(option, enabledKeys);

                boolean hasShort = false;
                int keyIndex = 0;

                for (String key : enabledKeys) {
                    if (keyIndex++ > 0)
                        line.append(", ");
                    if (key.length() == 1)
                        line.append('-' + key);
                    else
                        line.append("--" + key);
                    hasShort |= key.length() == 1;
                }

                if (!hasShort)
                    line.insert(tabSize, "    ");

                String valueHint = option.getValueHint();
                if (!Nullables.isEmpty(valueHint)) {
                    line.append('=');
                    line.append(valueHint);
                }
                offset = line.length();

                String description = option.getDescription().toString();
                if (offset >= descriptionColumn && !description.isEmpty()) {
                    line.append('\n');
                    offset = 0;
                }
                while (offset < descriptionColumn) {
                    offset++;
                    line.append(' ');
                }
                line.append(description);

                buffer.append(line);
                buffer.append('\n');
            } // for selection.

            group = group.getParent();
        } // for group
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