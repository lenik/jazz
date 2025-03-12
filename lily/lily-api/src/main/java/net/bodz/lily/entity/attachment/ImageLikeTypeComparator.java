package net.bodz.lily.entity.attachment;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class ImageLikeTypeComparator
        extends AbstractNonNullComparator<IImageLike> {

    final ImageType type;

    public ImageLikeTypeComparator(ImageType type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    @Override
    public int compareNonNull(IImageLike o1, IImageLike o2) {
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

        // just preserve the order
        return -1;
    }


}