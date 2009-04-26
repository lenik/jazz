package net.bodz.dist.pro.lic;

public interface SecurityInfoProvider {

    PrepareLoginInfo prepareLogin();

    Session login(LoginInfo loginInfo);

}
