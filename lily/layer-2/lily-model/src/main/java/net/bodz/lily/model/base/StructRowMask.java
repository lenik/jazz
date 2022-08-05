package net.bodz.lily.model.base;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import net.bodz.bas.c.type.NameConventionTypeMapper;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VarMapLoader;

/**
 * @see net.bodz.lily.model.base.CoObject
 */
public class StructRowMask
        implements
            IVarMapForm {

    DateTimeRange creation = new DateTimeRange();
    DateTimeRange lastmod = new DateTimeRange();

    IntegerRange versionRange;

    public DateTimeRange getCreation() {
        return creation;
    }

    public void setCreation(DateTimeRange creation) {
        this.creation = creation;
    }

    public DateTimeRange getLastmod() {
        return lastmod;
    }

    public void setLastmod(DateTimeRange lastmod) {
        this.lastmod = lastmod;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        VarMapLoader loader = new VarMapLoader();
        loader.load(getClass(), this, map);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        // TODO Use reflection to write.
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    static NameConventionTypeMapper[] maskTmaps = {
            // TODO cache-enable?
            new NameConventionTypeMapper(null, "Mask", false), //
            new NameConventionTypeMapper("impl.", "Mask", false), //
            new NameConventionTypeMapper(null, 1, "db.", "Mask", false), //
    };

    public static <M extends StructRowMask> Class<M> findMaskClass(Class<?> objClass) {
        for (NameConventionTypeMapper tmap : maskTmaps) {
            @SuppressWarnings("unchecked")
            Class<M> maskClass = (Class<M>) tmap.map(objClass);
            if (maskClass != null)
                return maskClass;
        }
        return null;
    }

}
