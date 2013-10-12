package net.bodz.mda.xmm.initial;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class EnclosedTagsTest {

    Map<String, String> tagMap;
    {
        tagMap = new HashMap<String, String>();
        tagMap.put("name", "lenik");
        tagMap.put("loc", "home");
    }

    @Test
    public void test1() {
        final EnclosedTags etags = new EnclosedTags(tagMap);
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return etags.process(input);
            }
        }, //
                EQ("hello", "hello"), //
                EQ("a-//<name>\n..\n..//</name>\n-z", "a-lenik-z"), //
                EQ("a-/* <loc> */..\n../* </loc> */-z", "a-home-z"), //
                EQ("a-//<name>\n...//</name>\n-/*<loc>*/.../*</loc>*/-z",
                        "a-lenik-home-z"), //
                END);
    }

}
