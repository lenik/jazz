package net.bodz.xml.models.pdb;

import org.junit.Test;

public class TableTest {

    public static Table category;
    static {
        category = new Table();
        category.setName("category");
        category.setLabel("书类");
        category.setDoc("描述书的分类信息");
        category.setCacheStrategy(Table.CACHE_DICT);
        category.setTableRole(Table.MAINTABLE);

    }

    @Test
    public void test() throws Exception {

    }

}
