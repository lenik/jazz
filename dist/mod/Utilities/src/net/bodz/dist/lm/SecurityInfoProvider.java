package net.bodz.dist.lm;

public interface SecurityInfoProvider {

    PrepareLoginInfo prepareLogin();

    Session login(LoginInfo loginInfo);

}
