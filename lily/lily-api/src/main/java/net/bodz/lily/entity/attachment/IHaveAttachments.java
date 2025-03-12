package net.bodz.lily.entity.attachment;

import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;
import net.bodz.lily.entity.manager.RowOpAware;

@RowOpAware(UpdateAttachments.class)
public interface IHaveAttachments {

    @NotNull
    default IAttachmentManifest attachmentManifest() {
        IAttachmentManifest a = attachmentManifest(true);
        if (a == null)
            throw new Error("null manifest");
        return a;
    }

    @Nullable
    IAttachmentManifest attachmentManifest(boolean create);

    @NotNull
    default List<? extends IAttachment> filter(Predicate<? super IAttachment> predicate) {
        IAttachmentManifest manifest = attachmentManifest(false);
        if (manifest == null)
            return Collections.emptyList();
        else
            return manifest.filter(predicate);
    }

    @Nullable
    default IAttachment find(@NotNull Predicate<? super IAttachment> predicate) {
        return find(predicate, 0);
    }

    @Nullable
    default IAttachment find(@NotNull Predicate<? super IAttachment> predicate, int index) {
        IAttachmentManifest manifest = attachmentManifest(false);
        if (manifest == null)
            return null;
        else
            return manifest.find(predicate, index);
    }

}
