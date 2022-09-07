package net.bodz.lily.test;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;

public class DataTester {

    public static void main(String[] args)
            throws Exception {
        DataContext dataContext = DataHub.getPreferredHub().getTest();
        UserMapper mapper = dataContext.getMapper(UserMapper.class);
        for (User user : mapper.all()) {
            XmlFn.dump(user, System.out);
            String json = JsonFn.toJson(user, JsonFormOptions.PRETTY);
            System.out.println(json);
        }
    }

}
