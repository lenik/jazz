package net.bodz.mda.xjdoc.model1;

import java.io.Serializable;
import java.util.Map.Entry;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.DomainStrings;
import net.bodz.bas.i18n.dom.XDomainString;

/**
 * @author Lenik <lenik@bodz.net>
 *         <p lang="zh-CN">
 *         谢继雷 <xjl@99jsj.com>
 */
public class Author
        implements Serializable {

    private static final long serialVersionUID = 1L;

    DomainString name;
    DomainString email;
    DomainString description;

    public DomainString getName() {
        return name;
    }

    public void setName(DomainString name) {
        this.name = name;
    }

    public DomainString getEmail() {
        return email;
    }

    public void setEmail(DomainString email) {
        this.email = email;
    }

    public DomainString getDescription() {
        return description;
    }

    public void setDescription(DomainString description) {
        this.description = description;
    }

    public DomainString compile() {
        return DomainStrings.join(name, email, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null)
            sb.append(name);
        if (description != null)
            sb.append(description);
        if (email != null)
            sb.append(email);
        return sb.toString();
    }

    public static Author parse(DomainString text) {
        DomainString name = new XDomainString();
        DomainString email = new XDomainString();
        DomainString description = new XDomainString();

        for (Entry<String, String> tr : text.entrySet()) {
            String domain = tr.getKey();
            String str = tr.getValue();

            String _name = str;
            String _email = null;
            String _description = null;

            int _lt = str.indexOf('<');
            int _gt = str.indexOf('>');
            if (_lt != -1 && _gt != -1) {
                _name = str.substring(0, _lt);
                _email = str.substring(_lt + 1, _gt);
                _description = str.substring(_gt + 1);
            }

            name.put(domain, _name);
            if (email != null)
                email.put(domain, _email);
            if (description != null)
                description.put(domain, _description);
        } // for
        Author author = new Author();
        author.setName(name);
        author.setEmail(email);
        author.setDescription(description);
        return author;
    }

}
