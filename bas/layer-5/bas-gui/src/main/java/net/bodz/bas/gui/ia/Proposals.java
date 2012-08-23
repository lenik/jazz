package net.bodz.bas.gui.ia;

public interface Proposals {

    Proposal retry = new Proposal('r', "retry");
    Proposal ignore = new Proposal('i', "ignore");
    Proposal cancel = new Proposal('c', "cancel");
    Proposal debug = new Proposal('d', "debug");

}
