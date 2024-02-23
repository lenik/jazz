package net.bodz.violet.schema.plan;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.bas.site.json.JsonArrayList;
import net.bodz.bas.site.json.JsonVarMap;
import net.bodz.bas.t.variant.IVariantMap;

@HaveAttachments
@Table(schema = "violet", name = "diary")
public class Diary
        extends _Diary_stuff {

    private static final long serialVersionUID = 1L;

    List<DiaryParty> parties;

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
