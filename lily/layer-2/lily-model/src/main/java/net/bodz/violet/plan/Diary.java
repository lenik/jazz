package net.bodz.violet.plan;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.site.file.UploadHint;
import net.bodz.bas.site.json.JsonArrayList;
import net.bodz.bas.site.json.JsonVarMap;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

@Table(name = "diary")
@IdType(Long.class)
@UploadHint
public class Diary
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    DiaryCategory category;
    DiaryPhase phase;
    int value;

    List<DiaryParty> parties;

    public Diary() {
    }

    public DiaryCategory getCategory() {
        return category;
    }

    public void setCategory(DiaryCategory category) {
        this.category = category;
    }

    public DiaryPhase getPhase() {
        return phase;
    }

    public void setPhase(DiaryPhase phase) {
        this.phase = phase;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    protected DiaryProperties createProperties() {
        return new DiaryProperties();
    }

    @Override
    public DiaryProperties getProperties() {
        return (DiaryProperties) super.getProperties();
    }

    public List<DiaryParty> getParties() {
        return parties;
    }

    public void setParties(List<DiaryParty> parties) {
        this.parties = parties;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);

        JsonArrayList partiesList = (JsonArrayList) map.get("parties");
        if (partiesList != null) {
            List<DiaryParty> parties = new ArrayList<>();
            for (Object item : partiesList) {
                JsonVarMap partyMap = (JsonVarMap) item;
                DiaryParty party = new DiaryParty();
                party.readObject(partyMap);
                if (party.getDiary() == null)
                    party.setDiary(this);
                parties.add(party);
            }
            setParties(parties);
        }
    }

}
