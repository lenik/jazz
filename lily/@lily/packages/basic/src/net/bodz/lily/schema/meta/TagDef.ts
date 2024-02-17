
import { * as validators } from "./PersonValidators";
import type { _TagDef_stuff } from "./_TagDef_stuff";

export class TagDef extends _TagDef_stuff {
    static TYPE = new TagDefType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
