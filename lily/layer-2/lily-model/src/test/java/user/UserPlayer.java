package user;

import java.util.Random;

import net.bodz.bas.db.test.DatabaseTester;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;

public class UserPlayer
        extends DatabaseTester {

    static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args)
            throws Exception {
        new UserPlayer().main();
    }

    @Override
    public void main()
            throws Exception {
        UserMapper userMapper = getMapper(UserMapper.class);
        // for (User user : userMapper.all()) dump(user);
        User user = userMapper.select(91);
        dump(user);

        user = new User();
        user.setCodeName("Test" + random.nextInt());
        user.setLabel("Test1");
        user.getSecret().setPassword("hello");
        userMapper.insert(user);
        int id = user.getId();

        user.setLabel("Changed");
        userMapper.update(user);

        userMapper.delete(id);
    }

    static void dump(User user) {
        ITreeOut out = TreeOutImpl.from(Stdio.cout);

        Group g0 = user.getPrimaryGroup();
        out.println(user.getId() + ": " + user.getCodeName() + ": " + user.getLabel());

        out.enter();
        if (g0 != null)
            out.println("Primary group: " + g0.getId() + " - " + g0.getLabel());

        for (Group g : user.getGroups())
            out.println("Group: " + g.getId() + "-" + g.getLabel());

        out.leave();
    }

}