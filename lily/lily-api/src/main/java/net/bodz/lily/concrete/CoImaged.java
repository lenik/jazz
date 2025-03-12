package net.bodz.lily.concrete;

import javax.persistence.Column;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.lily.entity.attachment.DefaultAttachmentManifest;
import net.bodz.lily.entity.attachment.IHaveAttachments;
import net.bodz.lily.entity.attachment.IHaveImages;
import net.bodz.lily.entity.attachment.IHaveVideos;
import net.bodz.lily.entity.attachment.MutableAttachment;
import net.bodz.lily.entity.esm.DTColumn;
import net.bodz.lily.meta.FieldGroupVue;

@FieldGroupVue
@HaveAttachments
@TsTyped
@TypeParameters({ TypeParamType.ID_TYPE })
public class CoImaged<Id>
        extends IdEntity<Id>
        implements IHaveProperties,
                   IHaveAttachments,
                   IHaveImages<MutableAttachment>,
                   IHaveVideos<MutableAttachment>,
                   IHaveFiles {

    private static final long serialVersionUID = 1L;

    public static final String FIELD_PROPERTIES = "props";
    public static final String FIELD_FILES = "files";

    private JsonVariant properties;
    private DefaultAttachmentManifest attachmentManifest;

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
    public synchronized JsonVariant getFiles() {
        return JsonFn.toJsonVar(attachmentManifest);
    }

    @Override
    public synchronized void setFiles(JsonVariant files)
            throws ParseException {
        attachmentManifest = new DefaultAttachmentManifest();
        attachmentManifest.jsonIn(files);
    }

    @Override
    public synchronized DefaultAttachmentManifest attachmentManifest(boolean create) {
        if (attachmentManifest == null)
            if (create)
                attachmentManifest = new DefaultAttachmentManifest();
        return attachmentManifest;
    }

}
