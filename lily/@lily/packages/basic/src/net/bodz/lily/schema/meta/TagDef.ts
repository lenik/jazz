import _TagDef_stuff from "./_TagDef_stuff";
import { _TagDef_stuffTypeInfo } from "./_TagDef_stuffTypeInfo";

export class TagDef extends _TagDef_stuff {
    static TYPE = new _TagDef_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TagDef;
