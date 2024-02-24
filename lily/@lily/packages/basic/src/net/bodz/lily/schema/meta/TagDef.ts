import TagDefTypeInfo from "./TagDefTypeInfo";
import _TagDef_stuff from "./_TagDef_stuff";

export class TagDef extends _TagDef_stuff {
    static TYPE = new TagDefTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TagDef;
