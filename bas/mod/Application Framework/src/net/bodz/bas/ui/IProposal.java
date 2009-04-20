package net.bodz.bas.ui;

public interface IProposal extends Comparable<IProposal> {

    int getPriority();

    char getMnemonic();

    String getName();

    String getDescription();

}
