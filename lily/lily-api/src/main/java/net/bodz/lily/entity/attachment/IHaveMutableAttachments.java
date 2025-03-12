package net.bodz.lily.entity.attachment;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;
import net.bodz.lily.entity.manager.RowOpAware;

@RowOpAware(UpdateAttachments.class)
public interface IHaveMutableAttachments<A extends MutableAttachment>
        extends IHaveAttachments {

    @NotNull
    @Override
    default IMutableAttachmentManifest<A> attachmentManifest() {
        IMutableAttachmentManifest<A> manifest = attachmentManifest(true);
        if (manifest == null)
            throw new Error("null manifest");
        return manifest;
    }

    @Nullable
    @Override
    IMutableAttachmentManifest<A> attachmentManifest(boolean create);

    @NotNull
    default List<A> filter(Predicate<? super IAttachment> predicate) {
        IMutableAttachmentManifest<A> manifest = attachmentManifest(false);
        if (manifest == null)
            return Collections.emptyList();
        else
            return manifest.filter(predicate);
    }

    default void filter(@NotNull Predicate<? super IAttachment> predicate, @NotNull Collection<? extends A> replacement) {
        IMutableAttachmentManifest<A> manifest = attachmentManifest();
        manifest.filter(predicate, replacement);
    }

    @Nullable
    default A find(@NotNull Predicate<? super IAttachment> predicate) {
        return find(predicate, 0);
    }

    @Nullable
    default A find(@NotNull Predicate<? super IAttachment> predicate, int index) {
        IMutableAttachmentManifest<A> manifest = attachmentManifest(false);
        if (manifest == null)
            return null;
        else
            return manifest.find(predicate, index);
    }

    default void find(@NotNull Predicate<? super IAttachment> predicate, @Nullable A replacement) {
        find(predicate, 0, replacement);
    }

    default void find(@NotNull Predicate<? super IAttachment> predicate, int index, @Nullable A replacement) {
        if (replacement == null) {
            IAttachmentManifest manifest = attachmentManifest(false);
            if (manifest != null)
                filter(predicate, Collections.emptyList());
        } else {
            IMutableAttachmentManifest<A> manifest = attachmentManifest();
            manifest.find(predicate, index, replacement);
        }
    }

}
