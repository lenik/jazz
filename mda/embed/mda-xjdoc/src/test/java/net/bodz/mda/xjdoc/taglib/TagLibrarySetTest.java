package net.bodz.mda.xjdoc.taglib;

import org.junit.Test;

import net.bodz.mda.xjdoc.model.IDocTag;

public class TagLibrarySetTest {

    @Test
    public void test() {
        TagLibraryLoader loader = TagLibraryLoader.getInstance();
        TagLibrarySet tagLib = loader.parseSet("*");
        System.out.println(tagLib.getName());

        String root = tagLib.getRootTagName("mapper.xml");
        System.out.println(root);

        IDocTag<?> tag = tagLib.createTag("mapper.xml");
        System.out.println(tag);
    }

}
