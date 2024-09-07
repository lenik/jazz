package net.bodz.lily.concrete;

import javax.persistence.Column;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.lily.entity.attachment.AttachmentListingInProps;
import net.bodz.lily.entity.attachment.IAttachmentListing;
import net.bodz.lily.entity.attachment.util.IImagesInProps;
import net.bodz.lily.entity.esm.DTColumn;
import net.bodz.lily.meta.FieldGroupVue;

@FieldGroupVue
@HaveAttachments
@TsTyped
@TypeParameters({ TypeParamType.ID_TYPE })
public class CoImaged<Id>
        extends IdEntity<Id>
        implements
            IImagesInProps {

    private static final long serialVersionUID = 1L;

    public static final String FIELD_PROPERTIES = "props";

    private JsonVariant properties;

    @Column(name = FIELD_PROPERTIES)
    @DTColumn(hidden = true)
    @Override
    public JsonVariant getProperties() {
        return properties;
    }

    @Override
    public void setProperties(JsonVariant properties) {
        this.properties = properties;
    }

    static final String[] attachmentGroupKeys = { //
            K_IMAGES, //
    };

    @Override
    public IAttachmentListing listAttachments() {
        return new AttachmentListingInProps(this, attachmentGroupKeys);
    }

}
