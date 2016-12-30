package net.bodz.bas.db.sql.dialect;

public interface ISqlFormat {

    boolean isKeyword(String token);

    String qName(String name);

    String qString(Object string);

    String qDate(Object date);

    String getStatementTerminator();

}
