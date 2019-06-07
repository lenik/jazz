package net.bodz.violet.edu;

import net.bodz.lily.contact.Person;
import net.bodz.lily.test.TestSamples;

public class TestApplySamples
        extends TestSamples {

    public static TestApply build(TestPaper paper, Person person) {
        TestApply a = new TestApply();
        a.setLabel("testApply-1");
        a.setDescription("A testApply named testApply-1.");
        a.setPaper(paper);
        a.setPerson(person);
        return a;
    }

}
