package net.bodz.bas.cli.help;

import java.util.Arrays;
import java.util.Collection;

import net.bodz.bas.c.string.StringLengthComparator;
import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.util.Nullables;

public class AlignedOptionFormatter
        implements IOptionFormatter {

    int tabSize;
    int docColumn;
    char[] prefix;

    public int getTabSize() {
        return tabSize;
    }

    public void setTabSize(int tabSize) {
        prefix = new char[tabSize];
        Arrays.fill(prefix, ' ');
    }

    public int getDocColumn() {
        return docColumn;
    }

    public void setDocColumn(int docColumn) {
        this.docColumn = docColumn;
    }

    @Override
    public String format(Collection<IOption> options) {
        StringBuilder buffer = new StringBuilder(options.size() * 80);
        StringBuilder line = new StringBuilder(80);
        for (IOption opt : options) {
            String[] aliases = group.getAliases(opt);
            Arrays.sort(aliases, StringLengthComparator.INSTANCE);

            line.setLength(0);
            line.append(prefix);
            int col = tabSize;
            boolean hasshort = false;
            for (int i = 0; i < aliases.length; i++) {
                String nam = aliases[i];
                if (i > 0) {
                    line.append(',');
                    while ((++col % tabSize) != 0)
                        line.append(' ');
                }
                if (nam.length() == 1) {
                    line.append('-' + nam);
                    hasshort = true;
                    col += 2;
                } else {
                    line.append("--" + nam);
                    col += 3 + nam.length();
                }
            }
            if (!hasshort)
                line.insert(tabSize, prefix);
            String valueHint = opt.getValueHint();
            if (!Nullables.isEmpty(valueHint)) {
                if (aliases[aliases.length - 1].length() > 1)
                    line.append('=');
                line.append(valueHint);
            }

            String doc = opt.getDescription();

            col = line.length();
            if (col >= docColumn && !doc.isEmpty()) {
                line.append('\n');
                col = 0;
            }
            while (col < docColumn) {
                col++;
                line.append(' ');
            }

            buffer.append(line.toString());

            buffer.append(doc);
            buffer.append('\n');
        }
        return buffer.toString();
    }

}
