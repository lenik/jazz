package net.bodz.bas.content;

/**
 * <pre>
    content
        id
            *version

        buffer
            - pre-allocated space

        cache
            - redundants
            - generated contents
            - loaded resources

        *last updated time
 * </pre>
 */
public class ContentSelection {

    public final boolean all;

    public final boolean id;
    public final boolean idVersion;

    public final boolean buffer;
    public final boolean preAllocatedSpace;

    public final boolean cache;
    public final boolean redundants;
    public final boolean generatedContents;
    public final boolean loadedResources;

    public final boolean lastModifiedTime;

    ContentSelection(Builder builder) {
        all = builder.all;

        id = all || builder.id;
        idVersion = id || builder.idVersion;

        buffer = all || builder.buffer;
        preAllocatedSpace = buffer || builder.preAllocatedSpace;

        cache = all || builder.cache;
        redundants = cache || builder.redundants;
        generatedContents = cache || builder.generatedContents;
        loadedResources = cache || builder.loadedResources;

        lastModifiedTime = all || builder.lastModifiedTime;
    }

    public static class Builder {

        boolean all;

        boolean id;
        boolean idVersion;

        boolean buffer;
        boolean preAllocatedSpace;

        boolean cache;
        boolean redundants;
        boolean generatedContents;
        boolean loadedResources;

        boolean lastModifiedTime;

        public Builder all() {
            all = true;
            return this;
        }

        public Builder id() {
            id = true;
            return this;
        }

        public Builder idVersion() {
            idVersion = true;
            return this;
        }

        public Builder buffer() {
            buffer = true;
            return this;
        }

        public Builder preAllocatedSpace() {
            preAllocatedSpace = true;
            return this;
        }

        public Builder cache() {
            cache = true;
            return this;
        }

        public Builder redundants() {
            redundants = true;
            return this;
        }

        public Builder generatedContents() {
            generatedContents = true;
            return this;
        }

        public Builder loadedResources() {
            loadedResources = true;
            return this;
        }

        public Builder lastModifiedTime() {
            lastModifiedTime = true;
            return this;
        }

        public Builder bufferCache() {
            buffer = true;
            cache = true;
            return this;
        }

        public ContentSelection build() {
            return new ContentSelection(this);
        }

    }

}
