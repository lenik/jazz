package net.bodz.violet.art.impl;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.violet.art.Artifact;

public interface ArtifactMapper
        extends IMapperTemplate<Artifact, ArtifactMask> {

    Artifact selectByRfid(@Param("rfid") String rfidCode);

}
