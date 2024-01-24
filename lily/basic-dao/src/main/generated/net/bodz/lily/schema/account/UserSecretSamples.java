package net.bodz.lily.schema.account;

import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UserSecretSamples
        extends TestSampleBuilder {

    public User user;

    @Override
    public UserSecret build()
            throws Exception {
        UserSecret a = new UserSecret();
        a.setUser(user);
        a.setId(647669313);
        a.setPassword("Ouuokc_tuxiui. vy.");
        a.setQuestion("b yno@idwuu p_swpke iuuu sitaz; icu.");
        a.setAnswer("gobu, ju uoeu Ecinn.");
        return a;
    }

    @Override
    public UserSecretSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public UserSecret buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
