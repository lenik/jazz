package net.bodz.bas.db.ctx;

import java.util.Collection;

import org.junit.Test;

public class UnionDefaultContextIdResolverTest {

    IDefaultContextIdsResolver union = UnionDefaultContextIdResolver.getInstance();

    @Test
    public void testImpls() {
        Collection<String> ids = union.resolveContextIds(0);
        for (String id : ids)
            System.out.println(id);
    }

}
