package net.bodz.bas.db.sql.dialect;

public class SimpleSqlFormat
        extends AbstractSqlFormat {

    char leftNameQuoteChar;
    char rightNameQuoteChar;

    public SimpleSqlFormat(char leftNameQuoteChar, char rightNameQuoteChar, String... keywords) {
        super(keywords);
        this.leftNameQuoteChar = leftNameQuoteChar;
        this.rightNameQuoteChar = rightNameQuoteChar;
    }

    @Override
    public String qName(String name) {
        return leftNameQuoteChar + name + rightNameQuoteChar;
    }

}
