package net.bodz.bas.util.stat;

import org.junit.Assert;
import org.junit.Test;

public class StatFormatterTest
        extends Assert {

    StatNode createModel1() {
        TestCounterSpec spec = new TestCounterSpec();
        StatNode root = new StatNode(spec);
        root.resolveCounter("total", true).setValue(1000L);
        root.resolveCounter("foo/total", true).setValue(100L);
        root.resolveCounter("foo/delayed", true).setValue(20);
        root.resolveCounter("foo/special/total", true).setValue(300L);
        root.resolveCounter("foo/bar/total", true).setValue(5L);
        root.resolveCounter("foo/bar/delayed", true).setValue(3);
        return root;
    }

// @Test
    public void csvDefault() {
        StatNode root = createModel1();
        StatFormatter fmt = new StatFormatter();
        fmt.setFormat(StatDumpFormat.csv);
        fmt.setTreeGraph(false);
        fmt.setShowTitle(true);
        String result = fmt.format(root);
        System.out.print(result);
    }

// @Test
    public void csvQuoted() {
        StatNode root = createModel1();
        StatFormatter fmt = new StatFormatter();
        fmt.setFormat(StatDumpFormat.csv);
        fmt.setTreeGraph(false);
        fmt.setShowTitle(true);
        fmt.setQuoted(true);
        String result = fmt.format(root);
        System.out.print(result);
    }

    @Test
    public void treeDefault() {
        StatNode root = createModel1();
        StatFormatter fmt = new StatFormatter();
        fmt.setFormat(StatDumpFormat.table);
        fmt.setTreeGraph(true);
        fmt.setShowTitle(true);
        String result = fmt.format(root);
        System.out.print(result);
    }

}
