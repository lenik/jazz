package net.bodz.violet.schema.art;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.rtx.IAttributed;
import net.bodz.lily.repr.EntGroup;

/**
 * 物品
 */
@HaveAttachments
@Table(schema = "violet", name = "art")
public class Artifact
        extends _Artifact_stuff
        implements
            IAttributed {

    private static final long serialVersionUID = 1L;

    private Set<ArtifactTag> tags = new HashSet<>();
    private UOM uom = new UOM(UOMs.PIECE);
    private String uomProperty = "数量";
    private Map<UOM, Double> convMap = new HashMap<UOM, Double>();
    private int decimalDigits = 2;

    private ArtifactProperties properties;

    /**
     * 单位转换表
     */
    @OfGroup(EntGroup.Packaging.class)
    public Map<UOM, Double> getConvMap() {
        return convMap;
    }

    public void setConvMap(Map<UOM, Double> convMap) {
        if (convMap == null)
            throw new NullPointerException("convMap");
        this.convMap = convMap;
    }

    @Override
    public ArtifactProperties getProperties() {
        return properties;
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        if (properties == null)
            return null;
        else
            return properties.getAttribute(name, defaultValue);
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (properties == null)
            return;
        else
            properties.setAttribute(name, value);
    }

}
