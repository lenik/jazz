package net.bodz.xml.models.pdb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FieldTypeTest {

    @Test
    public void test1() throws Exception {
        assertEquals("Z1", new FieldType("Z1").toString());
        assertEquals("Z8", new FieldType("Z8").toString());
        assertEquals("Z", new FieldType("Z9").toString());
        assertEquals("Z10", new FieldType("Z10").toString());

        assertEquals("C[5]", new FieldType("C[5]").toString());
        assertEquals("C[5]", new FieldType("C[5, 5]").toString());
        assertEquals("U[,5]", new FieldType("U[1, 5]").toString());
        assertEquals("U", new FieldType("U[32]").toString());
        assertEquals("U[,]", new FieldType("U[,32]").toString());
        assertEquals("Y[,10]", new FieldType("Y[0, 10]").toString());

        assertEquals("I", new FieldType("I4").toString());
        assertEquals("I8", new FieldType("I8").toString());
        assertEquals("F", new FieldType("F4").toString());
    }

}
