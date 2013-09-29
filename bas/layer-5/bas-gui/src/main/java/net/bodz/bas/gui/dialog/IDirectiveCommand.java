package net.bodz.bas.gui.dialog;

public interface IDirectiveCommand
        extends Comparable<IDirectiveCommand> {

    int getPriority();

    char getMnemonic();

    String getName();

    String getDescription();

}
