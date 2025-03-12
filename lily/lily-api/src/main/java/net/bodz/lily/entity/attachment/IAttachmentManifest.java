package net.bodz.lily.entity.attachment;

import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;

public interface IAttachmentManifest
        extends IAttachmentPathChangeListener {

    List<? extends IAttachment> getData();

    int size();

    IAttachment get(int index);

    IAttachment get(@NotNull String key);

    /**
     * Find by name.
     */
    default IAttachment find(@NotNull String glob) {
        return find(glob, false, 0);
    }

    IAttachment find(@NotNull String glob, boolean ignoreCase, int index);

    @Nullable
    default IAttachment find(@NotNull Predicate<? super IAttachment> predicate) {
        return find(predicate, 0);
    }

    @Nullable
    IAttachment find(@NotNull Predicate<? super IAttachment> predicate, int index);

    @NotNull
    List<? extends IAttachment> filter(@NotNull Predicate<? super IAttachment> predicate);

    default AttThumbnail findThumbnail(IAttachment a, int size) {
        return findThumbnail(a, size, size);
    }

    default AttThumbnail findThumbnail(IAttachment a, int width, int height) {
        ImageType type = new ImageType();
        type.width = width;
        type.height = height;
        return findThumbnail(a, type);
    }

    AttThumbnail findThumbnail(IAttachment a, ImageType type);

    /**
     * Parameter value 0 means optional, anything.
     */
    default AttPreview findPreview(IAttachment a, int width, int height, int videoBitRate, int audioBitRate, String videoFormat, String audioFormat) {
        VideoType type = new VideoType();
        type.width = width;
        type.height = height;
        type.videoBitRate = videoBitRate;
        type.audioBitRate = audioBitRate;
        type.videoFormat = videoFormat;
        type.audioFormat = audioFormat;
        return findPreview(a, type);
    }

    AttPreview findPreview(IAttachment a, VideoType type);

    default AttScreenpack findScreenpack(IAttachment a, int width, int height, int quality, String format) {
        ImageType type = new ImageType();
        type.width = width;
        type.height = height;
        type.format = format;
        type.quality = quality;
        return findScreenpack(a, type);
    }

    AttScreenpack findScreenpack(IAttachment a, ImageType type);

}
