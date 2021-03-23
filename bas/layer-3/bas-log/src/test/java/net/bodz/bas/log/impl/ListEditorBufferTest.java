package net.bodz.bas.log.impl;

import org.junit.Assert;
import org.junit.Test;

public class ListEditorBufferTest
        extends Assert {

    @Test
    public void test1() {
        ListEditorBuffer buffer = new ListEditorBuffer();
        buffer.onRemoved(10, 20);
        buffer.onRemoved(10, 1);
        buffer.onRemoved(9, 1);
        buffer.onRemoved(30, 5);
        buffer.onRemoved(28, 5);
        buffer.onRemoved(30, 5);
        buffer.onChanged(40, 5);
        buffer.onChanged(38, 5);
        buffer.onChanged(45, 5);
        buffer.dump();
    }

}
