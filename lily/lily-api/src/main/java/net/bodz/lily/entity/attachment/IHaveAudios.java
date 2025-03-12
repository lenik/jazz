package net.bodz.lily.entity.attachment;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;
import net.bodz.bas.std.rfc.mime.ContentType;

public interface IHaveAudios<A extends MutableAttachment>
        extends IHaveMutableAttachments<A> {

    Predicate<IAttachment> TYPE_AUDIO = a -> //
            ContentType.forExtension(a.getExtension(), ContentType.DEFAULT).isAudio();

    @NotNull
    default List<A> getAudios() {
        return filter(TYPE_AUDIO);
    }

    default void setAudios(Collection<? extends A> replacement) {
        filter(TYPE_AUDIO, replacement);
    }

    @Nullable
    default A getDefaultAudio() {
        return find(TYPE_AUDIO);
    }

    default void setDefaultAudio(A audio) {
        find(TYPE_AUDIO, audio);
    }

}