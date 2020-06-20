package net.bodz.bas.sms.ucpaas;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonObject;

public class DeleteTemplateResult
        extends AbstractUcpaasResult {

    public String templateId;
    public String type;
    public String autograph;
    public String content;
    public String deleteDate;

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

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);
        templateId = o.getString("templateid");
        type = o.getString("type");
        autograph = o.getString("autograph");
        content = o.getString("content");
        deleteDate = o.getString("delete_date");
    }

}
