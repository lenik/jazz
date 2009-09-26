package net.bodz.xml.models.pdb;

import net.bodz.xml.util.XMLTest;

import org.junit.Test;

public class TableTest {

    public static Table categoryTable;
    static {
        categoryTable = new Table();
        categoryTable.setName("category");
        categoryTable.setLabel("书类");
        categoryTable.setDoc("描述书的分类信息");
        categoryTable.setCacheStrategy(Table.CACHE_DICT);
        categoryTable.setTableRole(Table.MAINTABLE);

        categoryTable.getFields().add(FieldTest.addressField);
        categoryTable.getFields().add(FieldTest.addressField);
        categoryTable.getFields().add(FieldTest.addressField);
        categoryTable.getFields().add(FieldTest.addressField);
    }

    @Test
    public void test() throws Exception {
        XMLTest.testMarshal(categoryTable);
    }

}
