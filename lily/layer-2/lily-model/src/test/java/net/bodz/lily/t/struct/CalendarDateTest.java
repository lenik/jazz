package net.bodz.lily.t.struct;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class CalendarDateTest
        extends Assert {

    @Test
    public void test()
            throws ParseException {
        CalendarDate date = new CalendarDate();
        date.setAsString("1922-4-05w7-1 13:04:56");
        System.out.println(date);
    }

    public static void main(String[] args)
            throws ParseException {
        CalendarDate wed = new CalendarDate();
        wed.setAsString("--w-3 ::");
        DateTime time = new DateTime();
        for (int i = 0; i < 100; i++) {
            DateTime future = wed.alignFrom(time);
            time = future.plusDays(1);

            int week = future.getWeekOfWeekyear();
            int weekDay = future.getDayOfWeek();

            System.out.println(future + " Week " + week + "/" + weekDay);
        }
    }

}
