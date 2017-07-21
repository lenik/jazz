package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

/**
 * 物品参数
 */
@IdType(Integer.class)
@Table(name = "artparm")
public class ArtifactParameter
        extends CoNode<ArtifactParameter, Integer> {

    private static final long serialVersionUID = 1L;

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
    }

}
