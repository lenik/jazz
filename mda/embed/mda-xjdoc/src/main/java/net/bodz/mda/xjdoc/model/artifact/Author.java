package net.bodz.mda.xjdoc.model.artifact;

import java.io.Serializable;
import java.util.Map.Entry;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom.DomainStrings;
import net.bodz.bas.i18n.dom.XiString;

/**
 * @author Lenik <lenik@bodz.net>
 *         <p lang="zh-CN">
 *         谢继雷 <xjl@99jsj.com>
 */
public class Author
        implements Serializable {

    private static final long serialVersionUID = 1L;

    iString name;
    iString email;
    iString description;

    public iString getName() {
        return name;
    }

    public void setName(iString name) {
        this.name = name;
    }

    public iString getEmail() {
        return email;
    }

    public void setEmail(iString email) {
        this.email = email;
    }

    public iString getDescription() {
        return description;
    }

    public void setDescription(iString description) {
        this.description = description;
    }

    public iString compile() {
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

    public static Author parse(iString text) {
        iString name = new XiString();
        iString email = new XiString();
        iString description = new XiString();

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
