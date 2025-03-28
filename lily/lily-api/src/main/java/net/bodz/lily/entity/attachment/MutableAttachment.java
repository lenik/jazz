package net.bodz.lily.entity.attachment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.lily.storage.IVolume;

public class MutableAttachment
        extends DefaultBackedFile
        implements IMutableAttachment {

    String label;
    String description;

    Map<Class<?>, Object> views;

    public MutableAttachment() {
    }


    public MutableAttachment path(String dirName, String fileName) {
        setPath(dirName, fileName);
        return this;
    }

    public MutableAttachment path(String path) {
        setPath(path);
        return this;
    }

    public MutableAttachment assign(IAttachment o)
            throws IOException {
        super.assign(o);
        this.label = o.getLabel();
        this.description = o.getDescription();
        return this;
    }

    public MutableAttachment assign(MutableAttachment o) {
        super.assign(o);
        this.label = o.label;
        this.description = o.description;
        return this;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public <T> T getView(Class<T> viewType) {
        if (views == null)
            return null;
        Object view = views.get(viewType);
        return viewType.cast(view);
    }

    public <T> void setView(@NotNull Class<T> viewType, @NotNull T view) {
        if (views == null)
            views = new LinkedHashMap<>();
        views.put(viewType, view);
    }

    public <T> void addView(@NotNull T view) {
        @SuppressWarnings("unchecked")
        Class<T> viewType = (Class<T>) view.getClass();
        setView(viewType, view);
    }

    @Override
    public synchronized void update(IVolume volume)
            throws IOException {
        super.update(volume);
        // update preview, scene cuts, ...
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts) {
        super.jsonIn(o, opts);
        label = o.getString(K_LABEL);
        description = o.getString(K_DESCRIPTION);
    }

    public static List<MutableAttachment> parseAttachments(JsonArray array) {
        List<MutableAttachment> result = new ArrayList<>();
        try {
            parseJsonArray(array, result, MutableAttachment::new);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void parseAttachments(JsonArray array, List<MutableAttachment> result) {
        try {
            parseJsonArray(array, result, MutableAttachment::new);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
