package net.bodz.swt.reflect;

import org.eclipse.swt.SWT;
import org.junit.Test;

/**
 * @lang es
 * @lang et
 */
public class SelectLanguageDialogTest
        extends BasicGUI {

    @Test
    public void test1()
            throws Exception {
        SelectLanguageDialog dialog = new SelectLanguageDialog(null, SWT.NONE, SelectLanguageDialogTest.class);
        String lang = dialog.open();
        System.out.println("You choosed: " + lang);
    }

}
