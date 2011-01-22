package net.bodz.swt.reflect;

import net.bodz.bas.a.DisplayName;
import net.bodz.swt.reflect.AboutDialog;

import org.junit.Test;

@DisplayName("My Product")
public class AboutDialogTest {

    @Test
    public void test() throws Exception {
        AboutDialog aboutDialog = new AboutDialog(AboutDialogTest.class);
        aboutDialog.open();
    }

}
