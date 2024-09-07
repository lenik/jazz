package net.bodz.lily.concrete;

import java.util.List;

import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.schema.account.User;

@FieldGroupVue
@IdType(Long.class)
@TsTyped
public class CoMail
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    User recipient;
    List<User> recipients;
    List<User> bcc;

    boolean read;

}
