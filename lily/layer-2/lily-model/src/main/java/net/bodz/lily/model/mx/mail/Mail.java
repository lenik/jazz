package net.bodz.lily.model.mx.mail;

import java.util.List;

import net.bodz.lily.model.base.security.User;
import net.bodz.lily.model.mx.base.Message;

public class Mail
        extends Message {

    private static final long serialVersionUID = 1L;

    User recipient;
    List<User> recipients;
    List<User> bcc;

    boolean read;

}
