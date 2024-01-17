package net.bodz.violet.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.art.ArtifactVote;

public class ArtifactVoteManager
        extends AbstractEntityManager<ArtifactVote, ArtifactVoteMapper> {

    public ArtifactVoteManager(DataContext dataContext) {
        super(dataContext, ArtifactVoteMapper.class);
    }

}
