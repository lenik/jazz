package net.bodz.bas.t.conv;

public interface IConverter<src_t, dest_t> {

    @SuppressWarnings("unchecked")
    default dest_t _convert(Object src) {
        src_t k = (src_t) src;
        return convert(k);
    }

    dest_t convert(src_t src);

    @SuppressWarnings("unchecked")
    default src_t _restore(Object dest) {
        dest_t ak = (dest_t) dest;
        return restore(ak);
    }

    src_t restore(dest_t dest);

}
