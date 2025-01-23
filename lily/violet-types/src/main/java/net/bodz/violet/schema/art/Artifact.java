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
import net.bodz.lily.entity.attachment.AttachmentListingInFiles;
import net.bodz.lily.entity.attachment.IAttachmentListing;
import net.bodz.lily.entity.attachment.util.IDocInFiles;
import net.bodz.lily.entity.attachment.util.IVideosInFiles;
import net.bodz.lily.repr.EntGroup;
import net.bodz.lily.schema.util.Uom;
import net.bodz.lily.schema.util.UomRow;

/**
 * 物品
 */
@Table(schema = Artifact.SCHEMA_NAME, name = Artifact.TABLE_NAME)
public class Artifact
        extends _Artifact_stuff
        implements
            IVideosInFiles,
            IDocInFiles,
            IAttrInProps {

    private static final long serialVersionUID = 1L;

    private Set<ArtifactTag> tags = new HashSet<>();
    private Map<Uom, Double> convMap = new HashMap<Uom, Double>();
    private String uomProperty = "数量";
    private int decimalDigits = 2;

    {
        uom = new UomRow(Uoms.PIECE);
    }

    /**
     * 单位转换表
     */
    @OfGroup(EntGroup.Packaging.class)
    public Map<Uom, Double> getConvMap() {
        return convMap;
    }

    public void setConvMap(Map<Uom, Double> convMap) {
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
        return new AttachmentListingInFiles(this, attachmentGroupKeys);
    }

}
