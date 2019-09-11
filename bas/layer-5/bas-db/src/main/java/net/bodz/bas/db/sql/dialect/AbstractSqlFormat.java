package net.bodz.bas.db.sql.dialect;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSqlFormat
        implements ISqlFormat {

    Set<String> keywords = new HashSet<String>();

    public AbstractSqlFormat(String... keywords) {
        for (String keyword : keywords)
            this.keywords.add(keyword.toLowerCase());
    }

    @Override
    public boolean isKeyword(String token) {
        if (token == null)
            return false;
        token = token.toLowerCase();
        return keywords.contains(token);
    }

    @Override
    public String qString(Object string) {
        if (string == null)
            return "null";
        return "'" + string + "'";
    }

    @Override
    public String qDate(Object date) {
        if (date == null)
            return "null";
        return "'" + date + "'";
    }

    @Override
    public String getStatementTerminator() {
        return ";";
    }

}
