package net.bodz.product.lm;

public interface SecurityInfoProvider {
    
    PrepareLoginInfo prepareLogin(); 
    Session login(LoginInfo loginInfo); 
    
}
