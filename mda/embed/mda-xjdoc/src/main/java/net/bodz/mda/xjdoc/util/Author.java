package net.bodz.mda.xjdoc.util;

import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.i18n.dom.DomainString;
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
        Set<String> domains = new TreeSet<String>();
        for (String tr : name.keySet())
            domains.add(tr);
        for (String tr : email.keySet())
            domains.add(tr);
        for (String tr : description.keySet())
            domains.add(tr);

        DomainString composite = new XDomainString();
        for (String domain : domains) {

        }

        return composite;
    }

    @Override
    public String toString() {
        return super.toString();
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
