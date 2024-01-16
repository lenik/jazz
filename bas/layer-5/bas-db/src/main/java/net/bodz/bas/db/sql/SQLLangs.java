package net.bodz.bas.db.sql;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.fmt.records.CsvRecords;
import net.bodz.bas.fmt.records.CsvRow;
import net.bodz.bas.io.res.ResFn;

public class SQLLangs {

    static Map<String, SQLLang> langs = new HashMap<>();
    static {
        load();
    }

    static void load() {
        URL csv = SQLLangs.class.getResource("keywords.csv");
        try {
            CsvRow head = null;
            int nFields = 0;
            for (CsvRow row : new CsvRecords(ResFn.url(csv))) {
                if (head == null) {
                    head = row;
                    nFields = head.size();
                } else {
                    String keyword = row.get(0);
                    keyword = keyword.toLowerCase();

                    for (int j = 1; j < nFields; j++) {
                        String usage = row.get(j);
                        if (usage.trim().isEmpty())
                            continue;

                        String langNameVer = head.get(j);
                        int dash = langNameVer.lastIndexOf('-');
                        String name = langNameVer;
                        String version = null;
                        if (dash != -1) {
                            name = langNameVer.substring(0, dash);
                            version = langNameVer.substring(dash + 1);
                        }

                        SQLLang lang = langs.get(name);
                        if (lang == null) {
                            lang = new SQLLang(name, version);
                            langs.put(name, lang);
                        }
                        lang.addKeyword(keyword);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static final String K_DB2 = "DB2";
    static final String K_POSTGRESQL = "PostgreSQL";
    static final String K_MYSQL = "MySQL";
    static final String K_SQL_SERVER = "SQL Server";
    static final String K_MIMER_SQL = "Mimer SQL";
    static final String K_TERADATA = "Teradata";
    static final String K_ORACLE = "Oracle";
    static final String K_SQL = "SQL";

    public static final SQLLang DB2 = langs.get(K_DB2);
    public static final SQLLang PostgreSQL = langs.get(K_POSTGRESQL);
    public static final SQLLang MySQL = langs.get(K_MYSQL);
    public static final SQLLang SQL_Server = langs.get(K_SQL_SERVER);
    public static final SQLLang Mimer_SQL = langs.get(K_MIMER_SQL);
    public static final SQLLang Teradata = langs.get(K_TERADATA);
    public static final SQLLang Oracle = langs.get(K_ORACLE);
    public static final SQLLang SQL = langs.get(K_SQL);

}
