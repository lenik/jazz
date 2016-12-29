package net.bodz.swt.c.dialog;

import org.eclipse.swt.SWT;
import org.junit.Assert;
import org.junit.Test;

/**
 * @lang es, et
 */
public class SelectLanguageDialogTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        // Locale.setDefault(Locale.CHINESE);
        SelectLanguageDialog dialog = new SelectLanguageDialog(null, SWT.NONE, SelectLanguageDialogTest.class);
        String lang = dialog.open();
        System.out.println("You choosed: " + lang);
    }

}
