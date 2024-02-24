import TagDef from "./TagDef";
import TagGroupDefTypeInfo from "./TagGroupDefTypeInfo";
import _TagGroupDef_stuff from "./_TagGroupDef_stuff";

export class TagGroupDef extends _TagGroupDef_stuff {
    static TYPE = new TagGroupDefTypeInfo();

    ortho: boolean
    tags?: TagDef[]

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TagGroupDef;
