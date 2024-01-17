package net.bodz.lily.criteria;

import net.bodz.bas.t.pojo.eg.Person;
import net.bodz.lily.criterion.FieldTypeInferrer;

public class FieldTypeInferrerTest {

    Class<?> testContext = Person.class;
    FieldTypeInferrer fti = new FieldTypeInferrer(testContext);

    void testGetFieldType(String... vec) {
        Class<?> type = fti.getFieldType(vec);
        System.out.print(testContext.getSimpleName());
        for (String a : vec)
            System.out.print("." + a);
        System.out.println(" => " + type.getName());
    }

    void testGetPersonFields() {
        testGetFieldType("name");
        testGetFieldType("age");
        testGetFieldType("location", "city");
        testGetFieldType("location", "postCode");
    }

    public static void main(String[] args) {
        new FieldTypeInferrerTest().testGetPersonFields();
    }

}
