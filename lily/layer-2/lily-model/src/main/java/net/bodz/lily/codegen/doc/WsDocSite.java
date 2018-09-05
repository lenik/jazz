package net.bodz.lily.codegen.doc;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.t.project.IJazzModule;
import net.bodz.bas.t.variant.IVariantMap;

public class WsDocSite
        implements IPathDispatchable {

    ModuleIndexer indexer = new ModuleIndexer();

    public AjaxResult getModules() {
        AjaxResult result = new AjaxResult();
        JsonWriter out = result.begin("modules").object();
        for (ModuleInfo modinfo : indexer.modules) {
            IJazzModule module = modinfo.getModule();
            out.key(module.getName());
            out.object();
            out.key("doc");
            out.value(modinfo.getDisplayName());
            out.key("entities");
            out.object();
            for (EntityInfo entity : modinfo.getEntities()) {
                out.key(entity.declaredClass.getName());
                out.value(entity.getDisplayName());
            }
            out.endObject();
            out.endObject();
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
            target = indexer.entityIndex;
            break;
        }

        if (target == null)
            return null;
        return PathArrival.shift(previous, target, tokens);
    }

}
