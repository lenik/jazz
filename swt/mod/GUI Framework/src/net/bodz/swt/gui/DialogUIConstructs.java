package net.bodz.swt.gui;

import net.bodz.bas.ui._UIConstructs;

import org.eclipse.swt.widgets.Shell;

/**
 * @test {@link DialogUIConstructsTest}
 */
public class DialogUIConstructs extends _UIConstructs {

    public DialogUIConstructs(Shell parent, int style) {
        super(new DialogInteraction(parent, style));
    }

    public DialogUIConstructs() {
        super(new DialogInteraction());
    }

}
