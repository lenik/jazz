package net.bodz.bas.a;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.net.URL;
import java.text.ParseException;
import java.util.Properties;

import net.bodz.bas.c1.annotations.DisplayName;
import net.bodz.bas.c1.annotations.Doc;
import net.bodz.bas.c1.util.Dates;
import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.fs.PlainFile;
import net.bodz.bas.fs.URLFile;
import net.bodz.bas.lang.Nullables;

@RcsKeywords(id = "$Id$")
public class A_bas {

    public static String getDisplayName(Class<?> type) {
        DisplayName dn = type.getAnnotation(DisplayName.class);
        String name = dn != null ? dn.value() : type.getSimpleName();
        return name;
    }

    public static <AM extends AnnotatedElement & Member> String getDisplayName(AM member) {
        DisplayName dn = member.getAnnotation(DisplayName.class);
        String name = dn != null ? dn.value() : member.getName();
        return name;
    }

    public static String getProgramName(Class<?> type) {
        return getProgramName(type, null);
    }

    public static String getProgramName(Class<?> type, Boolean toUpperCase) {
        ProgramName pn = type.getAnnotation(ProgramName.class);
        if (pn != null)
            return pn.value();
        String name = type.getSimpleName();
        if (toUpperCase != null)
            name = toUpperCase ? name.toUpperCase() : name.toLowerCase();
        return name;
    }

    public static String getDoc(AnnotatedElement aobject) {
        Doc adoc = Nullables.getAnnotation(aobject, Doc.class);
        if (adoc == null)
            return null;
        return getDoc(adoc);
    }

    public static String getDoc(Doc adoc) {
        if (adoc == null)
            return null;
        String doc = adoc.value();
        if (doc.startsWith("#")) {
            // #bundle.property
            throw new NotImplementedException();
        } else if (doc.startsWith("/")) {
            // doc = doc.substring(1);
            try {
                PlainFile file = new PlainFile(new File(doc));
                file.setCharset("utf-8");
                doc = file.forRead().readTextContents();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return doc;
    }

    public static VersionInfo parseId(String id) {
        id = id.substring(0, id.length() - 1);
        String[] parts = id.split("\\s+");
        VersionInfo info = new VersionInfo();
        switch (parts.length) {
        case 7: // state
            info.state = parts[6];
        case 6: // lenik
            info.author = parts[5];
        case 5: // 10:53:242
            long _time = 0;
            try {
                _time = Dates.timeFormat.parse(parts[4]).getTime();
            } catch (ParseException e) {
            }
            info.time += _time;
        case 4: // 2008-01-15
            long _date = 0;
            try {
                _date = Dates.dateFormat.parse(parts[3]).getTime();
            } catch (ParseException e) {
            }
            info.time += _date;
        case 3: // 784
            String[] revs = parts[2].split("\\.");
            info.revision = new int[revs.length];
            for (int i = 0; i < revs.length; i++)
                info.revision[i] = Integer.parseInt(revs[i]);
        case 2: // Rcs.java
            info.name = parts[1];
        case 1: // $Id:
        }
        return info;
    }

    public static VersionInfo parseId(RcsKeywords keywords) {
        return parseId(keywords.id());
    }

    public static VersionInfo parseId(Class<?> clazz) {
        return parseId(clazz.getAnnotation(RcsKeywords.class));
    }

    /**
     * @return <code>null</code> if build info is unknown.
     * @throws IOException
     */
    public static Properties getBuildInfo(Class<?> clazz)
            throws IOException {
        String resname = Nullables.getAnnotation(clazz, BuildInfo.class).value();
        if (resname == null)
            return null;
        URL url = clazz.getResource(resname);
        if (url == null)
            throw new NullPointerException("BuildInfo resource isn't existed: " + resname);
        return new URLFile(url).forLoad().loadProperties();
    }

}
