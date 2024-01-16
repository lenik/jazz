package net.bodz.bas.db.sql;

import java.util.Set;
import java.util.TreeSet;

public class SQLLang {

    String lang;
    String version;
    Set<String> keywords = new TreeSet<>();

    public SQLLang(String lang, String version) {
        this.lang = lang;
        this.version = version;
    }

    public boolean isReservedKeyword(String word) {
        String lcWord = word.toLowerCase();
        return keywords.contains(lcWord);
    }

    public void addKeyword(String keyword) {
        keyword = keyword.toLowerCase();
        keywords.add(keyword);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lang);
        if (version != null) {
            sb.append("-");
            sb.append(version);
        }
        sb.append(": ");
        // sb.append(keywords);
        sb.append(keywords.size());
        sb.append(" keywords");
        return sb.toString();
    }

}
