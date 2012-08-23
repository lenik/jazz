package net.bodz.swt.extern.widgets;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.swt.c.test.ControlTestApp;
import net.bodz.swt.c3.extern.DatePicker;

public class DatePickerTest {

    @Test
    public void test()
            throws Exception {
        ControlTestApp app = new ControlTestApp();
        DatePicker picker = new DatePicker(app.holder, SWT.NONE);
        Date date = new Date();
        picker.setDate(date);
        app.run();
    }

}