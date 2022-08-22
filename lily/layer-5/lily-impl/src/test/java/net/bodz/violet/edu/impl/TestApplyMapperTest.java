package net.bodz.violet.edu.impl;

import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.impl.PersonMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestApply;
import net.bodz.violet.edu.TestApplySamples;
import net.bodz.violet.edu.TestPaper;

public class TestApplyMapperTest
        extends AbstractTableTest<TestApply, TestApplyMask, TestApplyMapper> {

    @Override
    public TestApply buildSample() {
        TestPaper paper = tables.pickAny(TestPaperMapper.class, "testpaper");
        Person person = tables.pickAny(PersonMapper.class, "person");
        return TestApplySamples.build(paper, person);
    }

}
