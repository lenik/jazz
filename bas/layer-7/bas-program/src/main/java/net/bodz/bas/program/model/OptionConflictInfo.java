package net.bodz.bas.program.model;

import java.util.ArrayList;
import java.util.List;

public class OptionConflictInfo {

    public IOption preexisted;
    public List<String> aliases = new ArrayList<>();

    public OptionConflictInfo() {
    }

    public OptionConflictInfo(String alias, IOption option) {
        this.preexisted = option;
        aliases.add(alias);
    }

}
