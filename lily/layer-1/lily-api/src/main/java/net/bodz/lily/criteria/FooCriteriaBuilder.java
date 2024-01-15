package net.bodz.lily.criteria;

public class FooCriteriaBuilder
        extends CriteriaBuilder<FooCriteriaBuilder> {

    StringFieldCriteriaBuilder<FooCriteriaBuilder> name = string("name");
    NumberFieldCriteriaBuilder<FooCriteriaBuilder> age = number("age");
    NumberFieldCriteriaBuilder<FooCriteriaBuilder> salary = number("salary");
    StringFieldCriteriaBuilder<FooCriteriaBuilder> sex = string("sex");

    public static void main(String[] args) {
        FooCriteriaBuilder b = new FooCriteriaBuilder();
        b.name.like("tom%")//
                .or().age.lessThan(3).sex.eq("m").end() //
                        .not().salary.lessThan(1000).end();
        System.out.println(b.get().toJson());
    }

}
