package net.bodz.dist.ins;

import net.bodz.bas.types.TextMap.HashTextMap;
import net.bodz.dist.ins.builtins.GUISession;

public class TestSession extends GUISession {

    public TestSession() {
        super(new TestProject(), new HashTextMap<Object>(), null);
    }

}
