package net.bodz.lily.entity.manager.cmd;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;

public interface IDataFetcher {

    DataFetchResult fetchDataList(IVariantMap<String> q, boolean wantCount)
            throws ParseException;

}
