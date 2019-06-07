package net.bodz.violet.edu;

import net.bodz.lily.test.TestSamples;

public class TestPaperSamples
        extends TestSamples {

    public static TestPaper build(Course course) {
        TestPaper a = new TestPaper();
        a.setLabel("testPaper-1");
        a.setDescription("A testPaper named testPaper-1.");
        a.setCourse(course);
        return a;
    }

}
