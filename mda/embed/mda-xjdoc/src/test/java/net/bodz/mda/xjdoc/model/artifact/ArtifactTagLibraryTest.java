package net.bodz.mda.xjdoc.model.artifact;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

/**
 * Example class for the artifact tag library.
 *
 * @lang zh, en
 * @label Model-2 Tag Book
 * @label.zh.cn 二级模型标签书
 * @site http://www.example.com
 */
public class ArtifactTagLibraryTest
        extends Assert {

    ClassDoc doc;

    @Before
    public void load() {
        doc = Xjdocs.getDefaultProvider().getClassDoc(getClass());
    }

    @Test
    public void testGetLangs() {
        String[] langs = (String[]) doc.getTag("lang");
        Set<String> set = new TreeSet<String>(Arrays.asList(langs));
        assertEquals(2, set.size());
        assertTrue(set.contains("zh"));
        assertTrue(set.contains("en"));
    }

    @Test
    public void testGetSite()
            throws MalformedURLException {
        @SuppressWarnings("unchecked")
        Map<String, URL> site = (Map<String, URL>) doc.getTag("site");
        URL def = site.get(null);
        URL example = new URL("http://www.example.com");
        assertEquals(example, def);
    }

}
