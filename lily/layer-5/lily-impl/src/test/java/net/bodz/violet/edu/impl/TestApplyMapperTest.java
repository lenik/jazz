package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.impl.PersonMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.edu.TestApply;
import net.bodz.violet.edu.TestApplySamples;
import net.bodz.violet.edu.TestPaper;

public class TestApplyMapperTest
        extends AbstractMapperTest<TestApply, TestApplyMask, TestApplyMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public TestApply buildSample() {
        TestPaper paper = tables.pickAny(TestPaperMapper.class, "testpaper");
        Person person = tables.pickAny(PersonMapper.class, "person");
        return TestApplySamples.build(paper, person);
    }

}
