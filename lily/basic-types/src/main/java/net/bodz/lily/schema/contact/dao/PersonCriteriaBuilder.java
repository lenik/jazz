package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.criteria.NumberFieldCriterionBuilder;

public class PersonCriteriaBuilder
        extends _PersonCriteriaBuilder_stuff<PersonCriteriaBuilder> {

    @Override
    public NumberFieldCriterionBuilder<?, ?> getIdField() {
        return id;
    }

    @Override
    public void query(String text) {
        super.query(text);
        aboveOr().gender.like(text).end();
    }

}
