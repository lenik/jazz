package net.bodz.lily.entity.attachment;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;
import net.bodz.bas.std.rfc.mime.ContentType;

public interface IHaveImages<A extends MutableAttachment>
        extends IHaveMutableAttachments<A> {

    Predicate<IAttachment> TYPE_IMAGE = a -> //
            ContentType.forExtension(a.getExtension(), ContentType.DEFAULT).isImage();

    @NotNull
    default List<A> getImages() {
        return filter(TYPE_IMAGE);
    }

    default void setImages(Collection<? extends A> replacement) {
        filter(TYPE_IMAGE, replacement);
    }

    @Nullable
    default A getDefaultImage() {
        return find(TYPE_IMAGE);
    }

    default void setDefaultImage(A image) {
        find(TYPE_IMAGE, image);
    }

}