package net.bodz.bas.sms.ucpaas;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;

public class EditTemplateResult
        extends AbstractUcpaasResult {

    public String templateId;
    public int type;
    public String autograph;
    public String content;
    public String updateDate;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);
        templateId = o.getString("templateid");
        String _type = o.getString("type");
        if (_type != null)
            type = Integer.valueOf(_type);
        autograph = o.getString("autograph");
        content = o.getString("content");
        updateDate = o.getString("delete_date");
    }

}
