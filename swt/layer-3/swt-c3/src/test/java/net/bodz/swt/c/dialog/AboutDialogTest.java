package net.bodz.swt.c.dialog;

import org.junit.Test;

import net.bodz.swt.c.dialog.AboutDialog;

/**
 * @label My Product
 */
public class AboutDialogTest {

    @Test
    public void test()
            throws Exception {
        AboutDialog aboutDialog = new AboutDialog(AboutDialogTest.class);
        aboutDialog.open();
    }

}