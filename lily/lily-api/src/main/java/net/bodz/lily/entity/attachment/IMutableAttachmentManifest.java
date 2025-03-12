package net.bodz.lily.entity.attachment;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;

public interface IMutableAttachmentManifest<A extends MutableAttachment>
        extends IAttachmentManifest {

    // overrides

    @Override
    A get(int index);

    @Override
    A get(@NotNull String key);

    @Override
    default A find(@NotNull String glob) {
        return find(glob, false, 0);
    }

    @Override
    A find(@NotNull String glob, boolean ignoreCase, int index);

    @Nullable
    @Override
    default A find(@NotNull Predicate<? super IAttachment> predicate) {
        return find(predicate, 0);
    }

    @Nullable
    @Override
    A find(@NotNull Predicate<? super IAttachment> predicate, int index);

    @NotNull
    @Override
    List<A> filter(@NotNull Predicate<? super IAttachment> predicate);

    default void find(@NotNull Predicate<? super IAttachment> predicate, int index, @Nullable A replacement) {
        find(predicate, index, replacement, true);
    }

    void find(@NotNull Predicate<? super IAttachment> predicate, int index, @Nullable A replacement, boolean autoAppend);

    default void filter(@NotNull Predicate<? super IAttachment> predicate, @NotNull Collection<? extends A> replacement) {
        filter(predicate, replacement, true);
    }

    void filter(@NotNull Predicate<? super IAttachment> predicate, @NotNull Collection<? extends A> replacement, boolean autoAppend);

    void add(@NotNull A attachment);

    void set(int index, @NotNull A attachment);

    default void set(@NotNull String key, @NotNull A attachment) {
        set(key, attachment, true);
    }

    void set(@NotNull String key, @NotNull A attachment, boolean autoAppend);

    @NotNull
    A remove(int index);

    @Nullable
    A remove(@NotNull String key);

    void clear();

}
