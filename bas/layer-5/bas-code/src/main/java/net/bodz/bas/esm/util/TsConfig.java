package net.bodz.bas.esm.util;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.List;

import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.esm.BuiltinModules;
import net.bodz.bas.esm.EsmDomainMap;
import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.EsmModules;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.site.json.JsonMap;
import net.bodz.bas.t.list.SizedList;

public class TsConfig {

    /**
     * Find out the context module by looking into package.json.
     */
    public static EsmDomainMap buildDomainMap(File webDir) {
        NpmDir npmDir = NpmDir.closest(webDir);
        EsmModule contextModule = null;

        if (npmDir != null) {
            JsonObject packageJson;
            try {
                packageJson = npmDir.resolve();
            } catch (ParseException | IOException e) {
                throw new LoadException(e.getMessage(), e);
            }
            String pacakgeName = packageJson.getString("name");
            contextModule = new EsmModule(pacakgeName, "src");
        }

        return buildDomainMap(contextModule);
    }

    public static EsmDomainMap buildDomainMap(EsmModule contextModule) {
        EsmDomainMap domainMap = new EsmDomainMap.Builder()//
                .contextModule(contextModule) //
                .localPriority(BuiltinModules.PRIORITY_LOCAL)//
                .build();

        domainMap.addAll(EsmModules.domainMap);

        return domainMap;
    }

    static TypePoMap<Class<?>> equivTypeMap = new TypePoMap<>();

    static {
        equivTypeMap.put(JsonMap.class, JsonVariant.class);
        equivTypeMap.put(ZoneId.class, String.class);
        equivTypeMap.put(SizedList.class, List.class);
    }

    public static Class<?> getEquivType(Class<?> clazz) {
        Class<?> equiv = equivTypeMap.meet(clazz);
        return equiv != null ? equiv : clazz;
    }

}
