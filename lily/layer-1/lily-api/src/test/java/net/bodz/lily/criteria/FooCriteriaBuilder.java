package net.bodz.lily.criteria;

import java.math.BigDecimal;

import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.t.pojo.eg.Person;
import net.bodz.lily.criterion.CriterionJsonParser;
import net.bodz.lily.criterion.FieldTypeInferrer;
import net.bodz.lily.criterion.ICriterion;
import net.bodz.lily.criterion.PrettyFormatter;
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

    public static void main(String[] args)
            throws Exception {
        Class<?> context = Person.class;
        FieldTypeInferrer fti = new FieldTypeInferrer(context);

        FooCriteriaBuilder b = new FooCriteriaBuilder();
        b.name.notEq("Tom")//
                .or().age.lessThan(20).girl.isFalse() //
                /*  */.or().girl.isTrue().age.lessThan(30).end().end() //
                .not().salary.lessThan(new BigDecimal(1000)).end() //
                        .age
                .greaterThan(8).girl.isTrue();

        StringBuilder sb = new StringBuilder();
        b.get().accept(new SQLFormatter(sb));
        // System.out.println(sb);

        BCharOut buf = new BCharOut();
        b.get().accept(new PrettyFormatter(buf.indented()));
        System.out.println(buf);

        String json = JsonFn.toJson(b.get());
//        System.out.println(json);

        JsonVariant jv = JsonFn.parseAny(json);
        ICriterion restore = CriterionJsonParser.builder() //
                .typeInferrer(fti) //
                .build().parse(jv);
        json = JsonFn.toJson(restore);
        System.out.println(json);

    }

}
