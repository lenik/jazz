package net.bodz.bas.ui;

public class Proposal implements IProposal {

    private final int priority;
    private final char mnemonic;
    private final String name;
    private final String description;

    public Proposal(char mnemonic, String name) {
        this(0, mnemonic, name, null);
    }

    public Proposal(int priority, char mnemonic, String name, String description) {
        if (name == null)
            throw new NullPointerException("name"); 
        this.priority = priority;
        this.mnemonic = mnemonic;
        this.name = name;
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public char getMnemonic() {
        return mnemonic;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(IProposal o) {
        if (o == null) // null proposal has the least priority.
            return -1;
        int c = getPriority() - o.getPriority();
        if (c != 0)
            return c;
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        String s = name;
        String description = getDescription();
        if (description != null)
            s += ": " + description; 
        return s;
    }

}
