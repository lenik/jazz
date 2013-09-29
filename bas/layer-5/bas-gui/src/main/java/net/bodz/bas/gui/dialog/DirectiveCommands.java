package net.bodz.bas.gui.dialog;

public interface DirectiveCommands {

    DirectiveCommand retry = new DirectiveCommand('r', "retry");
    DirectiveCommand ignore = new DirectiveCommand('i', "ignore");
    DirectiveCommand cancel = new DirectiveCommand('c', "cancel");
    DirectiveCommand debug = new DirectiveCommand('d', "debug");

}
