package net.bodz.lily.criteria;

import net.bodz.lily.criterion.FieldLike;
import net.bodz.lily.criterion.ICriterion;

public class StringFieldCriterionBuilder<fin_target>
        extends FieldCriterionBuilder<fin_target, StringFieldCriterionBuilder<fin_target>, String> {

    public StringFieldCriterionBuilder(String fieldName, fin_target finishTarget,
            IReceiver<? super ICriterion> receiver) {
        super(fieldName, String.class, finishTarget, receiver);
    }

    public fin_target like(String pattern) {
        receiver.receive(new FieldLike(fieldName, true, pattern));
        return finishTarget;
    }

    public fin_target notLike(String pattern) {
        receiver.receive(new FieldLike(fieldName, false, pattern));
        return finishTarget;
    }

}
