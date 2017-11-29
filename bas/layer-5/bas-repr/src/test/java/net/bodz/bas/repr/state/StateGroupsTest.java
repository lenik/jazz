package net.bodz.bas.repr.state;

import org.junit.Assert;
import org.junit.Test;

public class StateGroupsTest
        extends Assert {

    @Test
    public void testGetStateByKey() {
        State ok = StateGroups.getState(StdStates.ID_OK);
        assertSame(StdStates.OK, ok);
    }

    @Test
    public void testGetStateByName() {
        State ok = StateGroups.getState(StdStates.OK.getName());
        assertSame(StdStates.OK, ok);
    }

}
