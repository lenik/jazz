package net.bodz.lily.criteria;

import net.bodz.lily.criterion.ICriterion;

public class CharFieldCriterionBuilder<fin_target>
        extends FieldCriterionBuilder<fin_target, CharFieldCriterionBuilder<fin_target>, Character> {

    public CharFieldCriterionBuilder(String fieldName, fin_target finishTarget,
            IReceiver<? super ICriterion> receiver) {
        super(fieldName, Character.class, finishTarget, receiver);
    }

    public fin_target eq(String value) {
        Character ch = value == null ? null : value.isEmpty() ? null : value.charAt(0);
        return send(makeEq(ch));
    }

    public fin_target notEq(String value) {
        Character ch = value == null ? null : value.isEmpty() ? null : value.charAt(0);
        return send(makeNotEq(ch));
    }

}
