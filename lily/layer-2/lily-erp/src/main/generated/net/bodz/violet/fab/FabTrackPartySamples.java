package net.bodz.violet.fab;

import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.fab.dao.FabTrackMapper;

public class FabTrackPartySamples
        extends TestSampleBuilder {

    public Person person;
    public FabTrack track;

    @Override
    public FabTrackParty build()
            throws Exception {
        FabTrackParty a = new FabTrackParty();
        a.setPerson(person);
        a.setTrack(track);
        a.setId(2433204462358435305L);
        a.setRole("ue. ji&ncfye rcuo'of Fio@ipa?");
        return a;
    }

    @Override
    public FabTrackPartySamples wireAny(IRandomPicker picker) {
        this.person = picker.pickAny(PersonMapper.class, "person");
        this.track = picker.pickAny(FabTrackMapper.class, "fabtrack");
        return this;
    }

    @Override
    public FabTrackParty buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
