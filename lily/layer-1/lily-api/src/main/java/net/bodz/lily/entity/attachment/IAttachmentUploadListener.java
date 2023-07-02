package net.bodz.lily.entity.attachment;

import java.io.IOException;
import java.util.List;

public interface IAttachmentUploadListener {

    void onUploaded(List<IAttachment> attachments)
            throws IOException;

}
