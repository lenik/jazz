package net.bodz.lily.concrete;

import java.util.Collections;
import java.util.List;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.lily.entity.attachment.DefaultAttachment;
import net.bodz.lily.entity.attachment.IAttachment;
import net.bodz.lily.entity.attachment.IHaveAttachments;

public interface IAttachmentsInFiles
        extends
            IHaveAttachments,
            IHaveFiles {

    default List<IAttachment> getAttachmentGroup(String groupKey) {
        JsonVariant files = getFiles();
        if (files != null) {
            JsonVariant array = files.get(groupKey);
            if (array != null) {
                List<IAttachment> list = DefaultAttachment.parseJsonArray(array.getArray());
                return list;
            }
        }
        return Collections.emptyList();
    }

    default void setAttachmentGroup(String groupKey, List<IAttachment> list) {
        JsonObject files = files();
        JsonArray ja = DefaultAttachment.toJsonArray(list);
        files.put(groupKey, ja);
    }

}
