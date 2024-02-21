package net.bodz.lily.concrete;

import java.time.ZonedDateTime;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.MutableVariant;
import net.bodz.lily.criteria.NumberFieldCriterionBuilder;
import net.bodz.lily.security.login.LoginToken;

/**
 * @see net.bodz.lily.concrete.CoObject
 */
public class CoObjectCriteriaBuilder<self_t extends CoObjectCriteriaBuilder<self_t>>
        extends StructRowCriteriaBuilder<self_t> {

    public final StringField label = string("a.label");
    public final StringField description = string("a.description");

//    String queryText;

    public final IntegerField priority = integer("priority");
    public final IntegerField flags = integer("flags");
    public final IntegerField state = integer("state");
    public final IntegerField ownerId = integer("uid");
    public final IntegerField ownerGroupId = integer("gid");
    public final IntegerField acl = integer("acl");

    public final DateField<ZonedDateTime> creationDate = date("creation", ZonedDateTime.class);
    public final DateField<ZonedDateTime> lastModifiedDate = date("lastmod", ZonedDateTime.class);
    public final DiscreteField<Object> properties = discrete("props", Object.class);

    public NumberFieldCriterionBuilder<?, ?> getIdField() {
        return null;
    }

    public void query(String text) {
        if (text == null || text.isEmpty())
            return;
        String pattern = "%" + text + "%";
        or().label.like(pattern).end();
        or().description.like(pattern).end();

        MutableVariant textVar = MutableVariant.wrap(text);
        if (StringPred.isDecimal(text)) {
            @SuppressWarnings("unchecked")
            NumberFieldCriterionBuilder<?, Number> idField = //
                    (NumberFieldCriterionBuilder<?, Number>) getIdField();
            if (idField != null) {
                Class<?> valueType = idField.getValueType();
                Number id = (Number) textVar.convert(valueType);
                or();
                idField.eq(id);
                end();
            }
        }
    }

    /**
     * XXX mybatis global vars won't work.
     */
    public LoginToken getLoginToken() {
        LoginToken token = LoginToken.fromRequest();
        return token;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);

        String searchText = map.getString("search-text");
        if (searchText != null && ! searchText.isEmpty())
            query(searchText);
    }

}
