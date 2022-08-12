package net.bodz.bas.sms.ucpaas;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;

public class GetTemplateResult
        extends AbstractUcpaasResult {

    public String templateId;
    public String type;
    public String autograph;
    public String content;

    public String examine;
    public String npreason;
    public String locked;

    public String updateDate;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getNpreason() {
        return npreason;
    }

    public void setNpreason(String npreason) {
        this.npreason = npreason;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);
        templateId = o.getString("templateid");
        type = o.getString("type");
        autograph = o.getString("autograph");
        content = o.getString("content");

        examine = o.getString("examine");
        npreason = o.getString("npreason");
        locked = o.getString("locked");

        updateDate = o.getString("delete_date");
    }

}
