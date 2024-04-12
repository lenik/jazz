package net.bodz.lily.criteria;

import java.util.Arrays;
import java.util.Collection;

import net.bodz.lily.criterion.Disjunction;
import net.bodz.lily.criterion.FieldLike;
import net.bodz.lily.criterion.ICriterion;

public class StringFieldCriterionBuilder<fin_target>
        extends FieldCriterionBuilder<fin_target, StringFieldCriterionBuilder<fin_target>, String> {

    public StringFieldCriterionBuilder(String fieldName, fin_target finishTarget,
            IReceiver<? super ICriterion> receiver) {
        super(fieldName, String.class, finishTarget, receiver);
    }

    public fin_target eq(char value) {
        return send(makeEq(String.valueOf(value)));
    }

    public fin_target notEq(char value) {
        return send(makeNotEq(String.valueOf(value)));
    }

    public fin_target like(String pattern) {
        receiver.receive(new FieldLike(fieldName, true, pattern));
        return finishTarget;
    }

    public fin_target notLike(String pattern) {
        receiver.receive(new FieldLike(fieldName, false, pattern));
        return finishTarget;
    }

    public fin_target likeAnyOf(String... patterns) {
        return likeAnyOf(Arrays.asList(patterns));
    }

    public fin_target likeAnyOf(Collection<String> patterns) {
        Disjunction or = new Disjunction();
        for (String pattern : patterns)
            or.add(new FieldLike(fieldName, true, pattern));
        receiver.receive(or);
        return finishTarget;
    }

}
