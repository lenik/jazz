package net.bodz.dist.pro.util;

import java.util.Date;

import net.bodz.bas.types.util.Objects;

public class Registrant {

    public int    type;
    public String name;
    public String email;
    public Date   expireDate;

    @Override
    public int hashCode() {
        int hash = 0x130057be * type;
        if (name != null)
            hash += 0x10b0bf7d * name.hashCode();
        if (email != null)
            hash += 0xa6dfa55b * email.hashCode();
        if (expireDate != null)
            hash += 0x89596ffe * expireDate.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Registrant))
            return false;
        Registrant u = (Registrant) obj;
        if (type != u.type)
            return false;
        if (Objects.equals(name, u.name))
            return false;
        if (Objects.equals(email, u.email))
            return false;
        if (Objects.equals(expireDate, u.expireDate))
            return false;
        return true;
    }

}
