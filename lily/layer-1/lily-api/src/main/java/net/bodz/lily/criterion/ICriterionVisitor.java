package net.bodz.lily.criterion;

public interface ICriterionVisitor {

    void not(Not not);

    void junction(Junction junction);

    void disjunction(Disjunction junction);

    void fieldBetween(FieldBetween<?> fieldBetween);

    void fieldCompare(FieldCompare<?> fieldCompare);

    void fieldIn(FieldIn<?> fieldIn);

    void fieldLike(FieldLike fieldLike);

    void fieldNull(FieldNull fieldNull);

    void fieldTrue(FieldTrue fieldTrue);

}
