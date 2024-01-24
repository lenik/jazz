package net.bodz.violet.schema.art.dao;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.violet.schema.art.Artifact;

public interface ArtifactMapper
        extends
            IEntityMapper<Artifact> {

    Artifact selectByRfid(@Param("rfid") String rfidCode);

}
