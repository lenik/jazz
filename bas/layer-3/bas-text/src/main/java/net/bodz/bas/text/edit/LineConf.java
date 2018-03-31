package net.bodz.bas.text.edit;

import java.util.List;
import java.util.regex.Pattern;

public class LineConf
        implements ITextPatcher {

    Pattern pattern;
    List<String> replacements;
    Integer maxOccurrences;
    boolean append;

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public List<String> getReplacements() {
        return replacements;
    }

    public void setReplacements(List<String> replacements) {
        this.replacements = replacements;
    }

    /**
     * Unlimit if not set.
     */
    public Integer getMaxOccurrences() {
        return maxOccurrences;
    }

    public void setMaxOccurrences(Integer maxOccurrences) {
        this.maxOccurrences = maxOccurrences;
    }

    /**
     * Append the replacement lines if pattern isn't found.
     */
    public boolean isAppend() {
        return append;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    @Override
    public void patch(List<String> lines) {
        lineConf(lines);
    }

    public int lineConf(List<String> lines) {
        int n = lines.size();
        int i = 0;
        int occurs = 0;
        while (i < n) {
            String line = lines.get(i);
            if (pattern.matcher(line).find()) {
                for (String repl : replacements)
                    if (++i < n)
                        lines.set(i, repl);
                    else {
                        lines.add(repl);
                        n++;
                    }
                occurs++;
                if (maxOccurrences != null && occurs >= maxOccurrences)
                    break;
            }
            i++;
        }
        if (occurs == 0 && append) {
            lines.addAll(replacements);
            occurs++;
        }
        return occurs;
    }

}
