package net.bodz.mda.xjdoc.util;

import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.i18n.dom.DomainString;

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
        for (Entry<String, DomainString> tr : name)
            domains.add(tr.getKey());
        for (Entry<String, DomainString> tr : email)
            domains.add(tr.getKey());
        for (Entry<String, DomainString> tr : description)
            domains.add(tr.getKey());

        DomainString composite = new DomainString();
        for (String domain : domains) {

        }
        return composite;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static Author parse(DomainString text) {
        DomainString name = new DomainString();
        DomainString email = new DomainString();
        DomainString description = new DomainString();

        for (Entry<String, DomainString> tr : text) {
            String domain = tr.getKey();
            DomainString a = tr.getValue();
            String domain2 = a.getDomain();
            String str = a.getValue();

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

            name.create(domain, _name);
            if (email != null)
                email.create(domain, _email);
            if (description != null)
                description.create(domain, _description);
        } // for
        Author author = new Author();
        author.setName(name);
        author.setEmail(email);
        author.setDescription(description);
        return author;
    }

}
