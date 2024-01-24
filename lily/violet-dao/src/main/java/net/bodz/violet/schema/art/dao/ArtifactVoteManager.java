package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.ArtifactVote;

public class ArtifactVoteManager
        extends AbstractEntityManager<ArtifactVote, ArtifactVoteMapper> {

    public ArtifactVoteManager(DataContext dataContext) {
        super(dataContext, ArtifactVoteMapper.class);
    }

}
