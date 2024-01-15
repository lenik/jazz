package net.bodz.lily.criteria;

public class FooCriteriaBuilder<fin_target, end_target>
        extends CriteriaBuilder<FooCriteriaBuilder<fin_target, end_target>> {

    StringFieldCriteriaBuilder<FooCriteriaBuilder> name = string("name");
    NumberFieldCriteriaBuilder<FooCriteriaBuilder> age = number("age");

}
