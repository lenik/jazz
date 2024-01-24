package net.bodz.lily.concrete;

import java.util.List;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.account.User;

@IdType(Long.class)
public class CoMail
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    User recipient;
    List<User> recipients;
    List<User> bcc;

    boolean read;

}
