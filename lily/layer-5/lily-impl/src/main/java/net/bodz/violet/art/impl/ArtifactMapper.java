package net.bodz.violet.art.impl;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.violet.art.Artifact;

public interface ArtifactMapper
        extends IEntityMapper<Artifact, ArtifactMask> {

    Artifact selectByRfid(@Param("rfid") String rfidCode);

}
