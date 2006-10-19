package net.bodz.sysy.cache;

import net.bodz.sysy.Resource;
import net.bodz.sysy.Version;

public interface VersionDB {

    Version getVersion(Resource res);
    
}
