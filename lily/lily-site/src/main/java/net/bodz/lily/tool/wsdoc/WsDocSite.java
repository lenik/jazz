package net.bodz.lily.tool.wsdoc;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.json.IJsonResponse;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.project.IJazzModule;
import net.bodz.bas.t.variant.IVariantMap;

public class WsDocSite
        implements IPathDispatchable {

    ModuleIndexer indexer;

    public WsDocSite() {
        indexer = ModuleIndexer.getInstance();
    }

    public IJsonResponse getModules() {
        JsonResult result = new JsonResult();
        JsonWriter out = result.begin("modules");
        out.object();
        for (ModuleInfo modinfo : indexer.getModules()) {
            IJazzModule module = modinfo.getModule();
            out.key(module.getName());
            out.object();
            {
                out.key("doc");
                out.value(modinfo.getDisplayName());

                out.key("entities");
                out.object();
                {
                    for (EntityInfo entity : modinfo.getEntities()) {
                        out.key(entity.declaredClass.getName());
                        out.value(entity.getDisplayName());
                    }
                    out.endObject();
                }

                out.endObject();
            }
        }
        out.endObject();
        return result.succeed();
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;
        switch (token) {
        case "entity":
            target = indexer.getEntityIndex();
            break;
        }

        if (target == null)
            return null;
        return PathArrival.shift(previous, this, target, tokens);
    }

}
