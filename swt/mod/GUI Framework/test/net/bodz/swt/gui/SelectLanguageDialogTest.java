package net.bodz.swt.gui;

import net.bodz.bas.a.Language;

import org.eclipse.swt.SWT;
import org.junit.Test;

@Language( { "es", "et" })
public class SelectLanguageDialogTest extends BasicGUI {

    @Test
    public void test1() throws Exception {
        SelectLanguageDialog dialog = new SelectLanguageDialog(null, SWT.NONE,
                SelectLanguageDialogTest.class);
        String lang = dialog.open();
        System.out.println("You choosed: " + lang);
    }

}
