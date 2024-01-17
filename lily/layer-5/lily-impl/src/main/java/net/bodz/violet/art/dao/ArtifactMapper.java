package net.bodz.violet.art.dao;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.violet.art.Artifact;

public interface ArtifactMapper
        extends
            IEntityMapper<Artifact, ArtifactCriteriaBuilder> {

    Artifact selectByRfid(@Param("rfid") String rfidCode);

}
