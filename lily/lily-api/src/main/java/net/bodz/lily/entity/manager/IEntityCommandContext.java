package net.bodz.lily.entity.manager;

import java.util.List;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.form.IFormDecl;
import net.bodz.bas.repr.form.PropertyChain;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public interface IEntityCommandContext {

    IDataApplication getDataApp();

    DataContext getDataContext();

    IEntityTypeInfo getEntityTypeInfo();

    default SelectOptions newSelectOptions() {
        return new SelectOptions();
    }

    default PropertyChain resolveFieldProp(String column) {
        IEntityTypeInfo typeInfo = getEntityTypeInfo();
        IFormDecl formDecl = typeInfo.getFormDecl();
        try {
            List<PropertyChain> chains = formDecl.resolvePattern(column);
            if (chains.size() != 1)
                throw new IllegalArgumentException("wildcard isn't allowed.");
            return chains.get(0);
        } catch (NoSuchPropertyException | ParseException e) {
            return null;
        }
    }

}
