package net.bodz.bas.ui.dialog;

public interface IDirectiveCommand
        extends Comparable<IDirectiveCommand> {

    int getPriority();

    char getMnemonic();

    String getName();

    String getDescription();

}
