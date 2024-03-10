package net.bodz.lily.schema.contact;

import net.bodz.bas.fmt.json.JsonFn;

public class PersonJson {

    public static void main(String[] args)
            throws Exception {
        Person p = new Person();
        System.out.println(p.getCategory());

//        List<IAttachment> v1 = p.getCategory().getImages();
//        List<IAttachment> v2 = p.getImages();
//        System.out.println(v1 == v2);

        Person p2 = (Person) p.clone();
        p2.reset(null);
        System.out.println(p2.getCategory());

        String json = JsonFn.toJsonPretty(p);
//        System.out.println(json);
    }

}
