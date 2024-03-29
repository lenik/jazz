package net.bodz.lily.util.sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.lily.security.IGroup;
import net.bodz.lily.security.IUser;

public class F_Log {

    String stereo;
    long id;
    String subject;
    String text;
    Date beginDate;
    Date creationDate;
    IUser owner;
    IGroup ownerGroup;

    public String getStereo() {
        return stereo;
    }

    public void setStereo(String stereo) {
        this.stereo = stereo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public IUser getOwner() {
        return owner;
    }

    public void setOwner(IUser owner) {
        this.owner = owner;
    }

    public IGroup getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(IGroup ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public static Map<String, List<F_Log>> group(Iterable<F_Log> logs) {
        Map<String, List<F_Log>> map = new TreeMap<String, List<F_Log>>();
        for (F_Log log : logs) {
            List<F_Log> list = map.get(log.stereo);
            if (list == null)
                map.put(log.stereo, list = new ArrayList<>());
            list.add(log);
        }
        return map;
    }

}
