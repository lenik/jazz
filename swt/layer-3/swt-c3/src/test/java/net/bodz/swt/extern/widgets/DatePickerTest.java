package net.bodz.swt.extern.widgets;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.junit.Test;

import net.bodz.swt.c.test.WidgetTester;
import net.bodz.swt.c3.extern.DatePicker;

public class DatePickerTest
        extends WidgetTester {

    @Test
    public void testPickDate()
            throws Exception {
        DatePicker picker = new DatePicker(body, SWT.NONE);
        Date date = new Date();
        picker.setDate(date);
    }

}
