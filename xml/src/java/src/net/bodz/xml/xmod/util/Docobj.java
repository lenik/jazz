package net.bodz.xml.xmod.modpdb;


public class Docobj {

    private String   id;
    protected String name;
    protected String qname;
    protected String opts;
    protected String cat;
    protected String disp;
    protected String icon;
    protected String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (this.id != null)
            Docobjs.unset(this.id);
        Docobjs.set(id, this);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpts() {
        return opts;
    }

    public void setOpts(String opts) {
        this.opts = opts;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
