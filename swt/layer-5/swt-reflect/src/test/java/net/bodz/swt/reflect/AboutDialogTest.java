package net.bodz.swt.reflect;

import org.junit.Test;

@DisplayName("My Product")
public class AboutDialogTest {

    @Test
    public void test()
            throws Exception {
        AboutDialog aboutDialog = new AboutDialog(AboutDialogTest.class);
        aboutDialog.open();
    }

}
