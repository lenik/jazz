package net.bodz.bas.cli.help;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.string.StringLengthComparator;
import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.util.Nullables;

public class AlignedOptionFormatter
        implements IOptionFormatter {

    public static final int DEFAULT_COLUMNS = 80;

    private int columns = DEFAULT_COLUMNS;
    private int tabSize;
    private int descriptionColumn;
    private char[] indentFiller;

    public AlignedOptionFormatter() {
        setTabSize(4);
        setDocColumn(20);
    }

    public int getTabSize() {
        return tabSize;
    }

    public void setTabSize(int tabSize) {
        indentFiller = new char[tabSize];
        Arrays.fill(indentFiller, ' ');
    }

    public int getDocColumn() {
        return descriptionColumn;
    }

    public void setDocColumn(int docColumn) {
        this.descriptionColumn = docColumn;
    }

    @Override
    public String format(IOptionGroup optionGroup, Iterable<IOption> selection) {
        StringBuilder buffer = new StringBuilder(100 * columns);
        StringBuilder line = new StringBuilder(columns);

        for (IOption option : selection) {
            Set<String> enabledKeys = new TreeSet<String>(StringLengthComparator.INSTANCE);
            optionGroup.fillEnabledKeys(option, enabledKeys);

            line.setLength(0);
            line.append(indentFiller);
            int offset = line.length();

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
        return buffer.toString();
    }

}
