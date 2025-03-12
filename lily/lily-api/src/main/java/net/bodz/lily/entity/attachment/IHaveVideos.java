package net.bodz.lily.entity.attachment;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;
import net.bodz.bas.std.rfc.mime.ContentType;

public interface IHaveVideos<A extends MutableAttachment>
        extends IHaveMutableAttachments<A> {

    Predicate<IAttachment> TYPE_VIDEO = a -> //
            ContentType.forExtension(a.getExtension(), ContentType.DEFAULT).isVideo();

    @NotNull
    default List<A> getVideos() {
        return filter(TYPE_VIDEO);
    }

    default void setVideos(Collection<? extends A> replacement) {
        filter(TYPE_VIDEO, replacement);
    }

    @Nullable
    default A getDefaultVideo() {
        return find(TYPE_VIDEO);
    }

    default void setDefaultVideo(A video) {
        find(TYPE_VIDEO, video);
    }

}