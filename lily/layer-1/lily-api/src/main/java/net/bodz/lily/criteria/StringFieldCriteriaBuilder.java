package net.bodz.lily.criteria;

import net.bodz.lily.criterion.FieldCriterion;
import net.bodz.lily.criterion.FieldLike;

public class StringFieldCriteriaBuilder<fin_target>
        extends FieldCriteriaBuilder<fin_target, StringFieldCriteriaBuilder<fin_target>, String> {

    public StringFieldCriteriaBuilder(String fieldName, fin_target finishTarget,
            IReceiver<? super FieldCriterion> receiver) {
        super(fieldName, finishTarget, receiver);
    }

    public fin_target like(String pattern) {
        receiver.accept(new FieldLike(fieldName, false, pattern));
        return finishTarget;
    }

    public fin_target notLike(String pattern) {
        receiver.accept(new FieldLike(fieldName, true, pattern));
        return finishTarget;
    }

}
