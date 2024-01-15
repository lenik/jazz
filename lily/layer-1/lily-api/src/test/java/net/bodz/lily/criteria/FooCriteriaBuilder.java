package net.bodz.lily.criteria;

import java.math.BigDecimal;

import net.bodz.lily.criterion.SQLFormatter;

public class FooCriteriaBuilder
        extends CriteriaBuilder<FooCriteriaBuilder> {

    StringField name = string("name");
    NumberField<Integer> age = number("age", Integer.class);
    BooleanField girl = bool("girl");
    NumberField<BigDecimal> salary = number("salary", BigDecimal.class);

    public FooCriteriaBuilder name(String name) {
        return this.name.eq(name);
    }

    public FooCriteriaBuilder age(int age) {
        return this.age.eq(age);
    }

    public static void main(String[] args) {
        FooCriteriaBuilder b = new FooCriteriaBuilder();
        b.name("Tom")//
                .or().age.lessThan(20).girl.isFalse().or().girl.isTrue().age.lessThan(30).end().end() //
                        .not().salary.lessThan(new BigDecimal(1000)).end() //
                                .age.greaterThan(8).name.like("tom%");
        ;

        StringBuilder sb = new StringBuilder();
        b.get().accept(new SQLFormatter(sb));
        System.out.println(sb);

        String json = b.get().toJson();
        System.out.println(json);
    }

}
