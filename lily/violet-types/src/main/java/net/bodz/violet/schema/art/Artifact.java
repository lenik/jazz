package net.bodz.violet.schema.art;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.lily.concrete.IAttrInProps;
import net.bodz.lily.entity.attachment.AttachmentListingInProps;
import net.bodz.lily.entity.attachment.IAttachmentListing;
import net.bodz.lily.entity.attachment.util.IDocInProps;
import net.bodz.lily.entity.attachment.util.IVideosInProps;
import net.bodz.lily.repr.EntGroup;

/**
 * 物品
 */
@Table(schema = "violet", name = "art")
public class Artifact
        extends _Artifact_stuff
        implements
            IVideosInProps,
            IDocInProps,
            IAttrInProps {

    private static final long serialVersionUID = 1L;

    private Set<ArtifactTag> tags = new HashSet<>();
    private Map<UOM, Double> convMap = new HashMap<UOM, Double>();
    private String uomProperty = "数量";
    private int decimalDigits = 2;

    {
        uom = new UOM(UOMs.PIECE);
    }

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

    @Derived
    @Transient
    public IArtifactExtras getExtras() {
        return new IArtifactExtrasInProps() {
            @Override
            public void setProperties(JsonVariant properties) {
                Artifact.this.setProperties(properties);
            }

            @Override
            public JsonVariant getProperties() {
                return Artifact.this.getProperties();
            }
        };
    }

    static final String[] attachmentGroupKeys = { K_IMAGES, K_VIDEOS, K_DOCS };

    @Override
    public IAttachmentListing listAttachments() {
        return new AttachmentListingInProps(this, attachmentGroupKeys);
    }

}
