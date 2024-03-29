/*
 * $Log: DiffTest.java,v $
 * Revision 1.4  2007/12/20 05:04:21  stuart
 * Can no longer import from default package.
 *
 * Revision 1.3  2006/09/28 17:10:41  stuart
 * New Diff test case.  DateDelta.
 *
 * Revision 1.2  2004/01/29 02:35:35  stuart
 * Test for out of bounds exception in UnifiedPrint.print_hunk.
 * Add setOutput() to DiffPrint.Base.
 *
 * Revision 1.1  2003/05/29 20:22:40  stuart
 * Test EditMask.getFix()
 *
 */
package net.bodz.bas.text.diff;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.compare.gnudiff.DiffEntry;
import net.bodz.bas.compare.gnudiff.DiffPrint;
import net.bodz.bas.compare.gnudiff.GNUDiff;
import net.bodz.bas.io.adapter.WriterPrintOut;

/**
 * Test Diff behavior.
 *
 * @author Stuart D. Gathman Copyright (C) 2002 Business Management Systems, Inc.
 */
public class DiffTest
        extends Assert {

    private static String[] f1 = { "hello" };
    private static String[] f2 = { "hello", "bye" };

    @Test
    public void testReverse() {
        GNUDiff diff = new GNUDiff(Arrays.asList(f1), Arrays.asList(f2));
        List<DiffEntry<Object>> script = diff.diff_2(true);
        assertTrue(script != null);
    }

    /** For Java versions without auto-boxing. */
    private Integer[] loadArray(int[] a) {
        Integer[] b = new Integer[a.length];
        for (int i = 0; i < a.length; ++i)
            b[i] = Integer.valueOf(a[i]);
        return b;
    }

    /**
     * This was causing an array out of bounds exception. Submitted by Markus Oellinger.
     */
    public void testSwap() {
        final Integer[] l1 = loadArray(new int[] { 1, 2, 4, 7, 9, 35, 56, 58, 76 });
        final Integer[] l2 = loadArray(new int[] { 1, 2, 4, 76, 9, 35, 56, 58, 7 });
        GNUDiff diff = new GNUDiff(Arrays.asList(l1), Arrays.asList(l2));
        List<DiffEntry<Object>> script = diff.diff_2(false);
        // script should have two changes
        assertTrue(script != null);
        DiffEntry<Object> ch0 = script.get(0);
        DiffEntry<Object> ch1 = script.get(1);
        assertEquals(1, ch0.inserted);
        assertEquals(1, ch0.deleted);
        assertEquals(3, ch0.index0);
        assertEquals(3, ch0.index1);
        assertEquals(1, ch1.inserted);
        assertEquals(1, ch1.deleted);
        assertEquals(8, ch1.index0);
        assertEquals(8, ch1.index1);
        // DiffPrint.Base p = new DiffPrint.UnifiedPrint(l1,l2);
        // p.print_script(script);
    }

    private static String[] test1 = { "aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii" };
    private static String[] test2 = { "aaa", "jjj", "kkk", "lll", "bbb", "ccc", "hhh", "iii", "mmm", "nnn", "ppp" };

    /**
     * Test context based output. Changes past the end of old file were causing an array out of
     * bounds exception. Submitted by Cristian-Augustin Saita and Adam Rabung.
     */
    @Test
    public void testContext()
            throws IOException {
        GNUDiff diff = new GNUDiff(Arrays.asList(test1), Arrays.asList(test2));
        List<DiffEntry<Object>> script = diff.diff_2(false);
        StringWriter wtr = new StringWriter();
        DiffPrint.Base p = new DiffPrint.UnifiedPrint(Arrays.asList(test1), Arrays.asList(test2), //
                new WriterPrintOut(wtr));
        // p.print_header("test1","test2");
        p.print_script(script);
        // System.out.println(wtr.toString());
        /*
         * FIXME: when DiffPrint is updated to diff-2.7, testfor expected output in wtr.toString().
         * diff-1.15 does not combine adjacent changes when they are close together.
         */
    }

}
