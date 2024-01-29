package net.bodz.lily.schema.contact;

import net.bodz.lily.schema.contact.dao.PersonMapper;
import net.bodz.lily.schema.contact.dao.PersonTagTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PersonTagSamples
        extends TestSampleBuilder {

    public PersonTagType tag;
    public Person person;

    @Override
    public PersonTag build()
            throws Exception {
        PersonTag a = new PersonTag();
        a.setTag(tag);
        a.setPerson(person);
        a.setId(1633337588);
        return a;
    }

    @Override
    public PersonTagSamples wireAny(IRandomPicker picker) {
        this.tag = picker.pickAny(PersonTagTypeMapper.class, "persontag");
        this.person = picker.pickAny(PersonMapper.class, "person");
        return this;
    }

    @Override
    public PersonTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
