package net.bodz.swt.widgets.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class Playback {

    public static void click(Button button) {
        button.setSelection(true);
        button.notifyListeners(SWT.Selection, null);
    }

}
