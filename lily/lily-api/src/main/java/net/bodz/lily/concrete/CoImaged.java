package net.bodz.lily.concrete;

import javax.persistence.Column;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.lily.entity.attachment.AttachmentListingInFiles;
import net.bodz.lily.entity.attachment.IAttachmentListing;
import net.bodz.lily.entity.attachment.util.IImagesInFiles;
import net.bodz.lily.entity.esm.DTColumn;
import net.bodz.lily.meta.FieldGroupVue;

@FieldGroupVue
@HaveAttachments
@TsTyped
@TypeParameters({ TypeParamType.ID_TYPE })
public class CoImaged<Id>
        extends IdEntity<Id>
        implements
            IHaveProperties,
            IImagesInFiles {

    private static final long serialVersionUID = 1L;

    public static final String FIELD_PROPERTIES = "props";
    public static final String FIELD_FILES = "files";

    private JsonVariant properties;
    private JsonVariant files;

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

    @Column(name = FIELD_FILES)
    @DTColumn(hidden = true)
    @Override
    public JsonVariant getFiles() {
        return files;
    }

    @Override
    public void setFiles(JsonVariant files) {
        this.files = files;
    }

    static final String[] attachmentGroupKeys = { //
            K_IMAGES, //
    };

    @Override
    public IAttachmentListing listAttachments() {
        return new AttachmentListingInFiles(this, attachmentGroupKeys);
    }

}
