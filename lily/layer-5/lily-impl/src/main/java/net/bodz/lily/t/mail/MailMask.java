package net.bodz.lily.t.mail;

import net.bodz.lily.t.base.CoMessageMask;

public class MailMask
        extends CoMessageMask {

    Integer recipientId;
    Integer bccId;
    Boolean read;

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public Integer getBccId() {
        return bccId;
    }

    public void setBccId(Integer bccId) {
        this.bccId = bccId;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

}
