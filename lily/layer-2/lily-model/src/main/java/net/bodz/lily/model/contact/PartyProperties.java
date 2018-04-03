package net.bodz.lily.model.contact;

import java.util.List;

import org.json.JSONArray;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.site.json.JsonMap;

public class PartyProperties
        extends JsonMap {

    private static final long serialVersionUID = 1L;

    static final String K_IMAGES = "images";

    @OfGroup(StdGroup.Visual.class)
    public List<ItemFile> getImages() {
        return getAttribute(K_IMAGES);
    }

    public void setImages(List<ItemFile> images) {
        setAttribute(K_IMAGES, images);
    }

    @Override
    protected boolean readFromJson(String key, Object val)
            throws ParseException {
        switch (key) {
        case K_IMAGES:
            setImages(ItemFile.convert((JSONArray) val));
            return true;
        }
        return super.readFromJson(key, val);
    }

}
