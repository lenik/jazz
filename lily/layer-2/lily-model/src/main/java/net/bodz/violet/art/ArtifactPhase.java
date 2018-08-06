package net.bodz.violet.art;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoPhase;

/**
 * 物品成熟度
 */
@IdType(Integer.class)
@Table(name = "artphase")
public class ArtifactPhase
        extends CoPhase {

    private static final long serialVersionUID = 1L;

    public ArtifactPhase() {
        super();
    }

    public ArtifactPhase(ArtifactPhase parent) {
        super(parent);
    }

}
