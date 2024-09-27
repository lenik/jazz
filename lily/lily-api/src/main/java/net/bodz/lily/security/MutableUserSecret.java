package net.bodz.lily.security;

public class MutableUserSecret
        implements
            IUserSecret {

    String password;

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
