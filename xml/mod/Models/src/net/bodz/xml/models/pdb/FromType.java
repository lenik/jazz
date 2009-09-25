package net.bodz.xml.models.pdb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fromType", propOrder = { "ref", "view", "join" })
public class FromType {

    @XmlAttribute
    protected String        alias;

    protected FromType.Ref  ref;
    protected View          view;
    protected FromType.Join join;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public FromType.Ref getRef() {
        return ref;
    }

    public void setRef(FromType.Ref ref) {
        this.ref = ref;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public FromType.Join getJoin() {
        return join;
    }

    public void setJoin(FromType.Join join) {
        this.join = join;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "left", "right" })
    public static class Join {

        @XmlAttribute
        protected String   mode;

        @XmlElement(required = true)
        protected FromType left;
        @XmlElement(required = true)
        protected FromType right;

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public FromType getLeft() {
            return left;
        }

        public void setLeft(FromType left) {
            this.left = left;
        }

        public FromType getRight() {
            return right;
        }

        public void setRight(FromType right) {
            this.right = right;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Ref {

        @XmlAttribute
        protected String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
