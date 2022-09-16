package net.bodz.lily.t.mail;

import java.util.ArrayList;
import java.util.List;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.security.User;
import net.bodz.lily.t.base.CoMessage;

@IdType(Long.class)
public class Mail
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    User recipient;
    List<User> recipients;
    List<User> bcc;

    boolean read;

    @Override
    public void reset() {
        super.reset();
        recipients = new ArrayList<>();
        bcc = new ArrayList<>();
    }

}
