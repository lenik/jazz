package net.bodz.bas.esm.util;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.List;

import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.esm.BuiltinModules;
import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.EsmModules;
import net.bodz.bas.esm.EsmPackageMap;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.site.json.JsonMap;
import net.bodz.bas.t.list.SizedList;

public class TsConfig {

    public static EsmPackageMap getPackageMap(File webDir) {
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

        return getPackageMap(contextModule);
    }

    public static EsmPackageMap getPackageMap(EsmModule contextModule) {
        EsmPackageMap packageMap = new EsmPackageMap.Builder()//
                .contextModule(contextModule) //
                .localPriority(BuiltinModules.PRIORITY_LOCAL)//
                .build();

        packageMap.putAll(EsmModules.packageMap);

        return packageMap;
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
