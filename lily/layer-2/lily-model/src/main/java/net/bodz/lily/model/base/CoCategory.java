package net.bodz.lily.model.base;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.site.json.JsonVarMap;
import net.bodz.bas.t.variant.IVariantMap;

public abstract class CoCategory<self_t extends CoCategory<self_t, Id>, Id>
        extends CoNode<self_t, Id> {

    private static final long serialVersionUID = 1L;

    CoCategoryProperties properties = new CoCategoryProperties();;

    public CoCategory() {
        super();
    }

    public CoCategory(self_t parent) {
        super(parent);
    }

    public CoCategoryProperties getProperties() {
        return properties;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException {
        super.readObject(map);
        JsonVarMap properties = (JsonVarMap) map.get("properties");
        if (properties != null)
            try {
                this.properties.readObject(properties.getWrapped());
            } catch (ParseException e) {
                throw new LoaderException(e.getMessage(), e);
            }
    }

}
