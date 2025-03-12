package net.bodz.lily.entity.attachment;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class VideoLikeTypeComparator
        extends AbstractNonNullComparator<IVideoLike> {

    final VideoType type;

    public VideoLikeTypeComparator(VideoType type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    @Override
    public int compareNonNull(IVideoLike o1, IVideoLike o2) {
        int d = type.compareGeom(o1, o2);
        if (d != 0)
            return d;

        if (type.format != null) {
            int d1 = type.format.equals(o1.getFormat()) ? 0 : 1;
            int d2 = type.format.equals(o2.getFormat()) ? 0 : 1;
            if (d1 != d2)
                return d1 - d2;
        }

        if (type.quality != 0) {
            int d1 = Math.abs(o1.getQuality() - type.quality);
            int d2 = Math.abs(o2.getQuality() - type.quality);
            if (d1 != d2)
                return d1 - d2;
        }

        if (type.videoFormat != null) {
            int d1 = type.videoFormat.equals(o1.getVideoFormat()) ? 0 : 1;
            int d2 = type.videoFormat.equals(o2.getVideoFormat()) ? 0 : 1;
            if (d1 != d2)
                return d1 - d2;

            if (type.videoBitRate != 0) {
                d1 = Math.abs(type.videoBitRate - o1.getVideoBitRate());
                d2 = Math.abs(type.videoBitRate - o2.getVideoBitRate());
                if (d1 != d2)
                    return d1 - d2;
            }
        }

        if (type.audioFormat != null) {
            int d1 = type.audioFormat.equals(o1.getAudioFormat()) ? 0 : 1;
            int d2 = type.audioFormat.equals(o2.getAudioFormat()) ? 0 : 1;
            if (d1 != d2)
                return d1 - d2;

            if (type.audioBitRate != 0) {
                d1 = Math.abs(type.audioBitRate - o1.getAudioBitRate());
                d2 = Math.abs(type.audioBitRate - o2.getAudioBitRate());
                if (d1 != d2)
                    return d1 - d2;
            }
        }

        // just preserve the order
        return -1;
    }

}
