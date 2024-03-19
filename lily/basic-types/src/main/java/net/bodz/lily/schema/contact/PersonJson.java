package net.bodz.lily.schema.contact;

import java.util.List;

import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.lily.entity.attachment.IAttachment;

public class PersonJson {

    public static void main(String[] args)
            throws Exception {
        Person p = new Person();
        List<IAttachment> v1 = p.getCategory().getImages();
        List<IAttachment> v2 = p.getImages();
        System.out.println(v1 == v2);

        String json = JsonFn.toJson(p);
        System.out.println(json);
    }

}
