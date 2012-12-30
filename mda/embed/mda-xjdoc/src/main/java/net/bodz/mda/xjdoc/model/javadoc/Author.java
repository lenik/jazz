package net.bodz.mda.xjdoc.model.javadoc;

import java.io.Serializable;

/**
 * @author Lenik <lenik@bodz.net>
 * @author.zh.cn 谢继雷 <xjl@99jsj.com>
 */
public class Author
        implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    String email;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null)
            sb.append(name);
        if (email != null)
            sb.append(" <" + email + ">");
        if (description != null)
            sb.append(" " + description);
        return sb.toString();
    }

    public static Author parse(String str) {
        String name = str;
        String email = null;
        String description = null;

        int _lt = str.indexOf('<');
        int _gt = str.indexOf('>');

        if (_lt != -1 && _gt != -1) {
            name = str.substring(0, _lt);

            email = str.substring(_lt + 1, _gt).trim();
            if (email.isEmpty())
                email = null;

            description = str.substring(_gt + 1).trim();
            if (description.isEmpty())
                description = null;
        }

        Author author = new Author();
        author.setName(name);
        author.setEmail(email);
        author.setDescription(description);
        return author;
    }

}
