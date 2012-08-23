package net.bodz.bas.gui.ia;

public interface IProposal
        extends Comparable<IProposal> {

    int getPriority();

    char getMnemonic();

    String getName();

    String getDescription();

}
