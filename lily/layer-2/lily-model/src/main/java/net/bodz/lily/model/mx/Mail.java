package net.bodz.lily.model.mx;

import java.util.ArrayList;
import java.util.List;

import net.bodz.lily.model.base.IdType;
import net.bodz.lily.model.base.security.User;
import net.bodz.lily.model.mx.base.CoMessage;

@IdType(Long.class)
public class Mail
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    User recipient;
    List<User> recipients;
    List<User> bcc;

    boolean read;

    @Override
    public void instantiate() {
        super.instantiate();
        recipients = new ArrayList<>();
        bcc = new ArrayList<>();
    }

}
