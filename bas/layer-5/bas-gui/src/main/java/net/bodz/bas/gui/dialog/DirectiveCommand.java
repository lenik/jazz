package net.bodz.bas.gui.dialog;

public class DirectiveCommand
        implements IDirectiveCommand {

    private final int priority;
    private final char mnemonic;
    private final String name;
    private final String description;

    public DirectiveCommand(char mnemonic, String name) {
        this(0, mnemonic, name, null);
    }

    public DirectiveCommand(int priority, char mnemonic, String name, String description) {
        if (name == null)
            throw new NullPointerException("name");
        this.priority = priority;
        this.mnemonic = mnemonic;
        this.name = name;
        this.description = description;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public char getMnemonic() {
        return mnemonic;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(IDirectiveCommand o) {
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
