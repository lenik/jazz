package net.bodz.swt.c.dialog;

import org.eclipse.swt.SWT;
import org.junit.Assert;
import org.junit.Test;

/**
 * @lang es
 * @lang et
 */
public class SelectLanguageDialogTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        SelectLanguageDialog dialog = new SelectLanguageDialog(null, SWT.NONE, SelectLanguageDialogTest.class);
        String lang = dialog.open();
        System.out.println("You choosed: " + lang);
    }

}
