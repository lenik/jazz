package net.bodz.xml.util;

import java.util.ArrayList;
import java.util.List;

public class TermDict {

    private List<String> terms;

    public TermDict() {
        terms = new ArrayList<String>();
        terms.add(null);
    }

    public void define(int id, String code) {
        while (id > terms.size())
            terms.add(null);
        if (id == terms.size())
            terms.add(code);
        else
            terms.set(id, code);
    }

    public Integer getId(String term) {
        if (term == null)
            throw new NullPointerException("term");
        int n = terms.size();
        for (int id = 1; id < n; id++) {
            String s = terms.get(id);
            if (s.equals(term))
                return id;
        }
        return null;
    }

    public String getTerm(int id) {
        if (id < 1 || id >= terms.size())
            throw new IllegalArgumentException("Bad id: " + id);
        String term = terms.get(id);
        return term;
    }

}
