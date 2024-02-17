
import { * as validators } from "./PersonValidators";
import type { _CategoryDef_stuff } from "./_CategoryDef_stuff";

export class CategoryDef extends _CategoryDef_stuff {
    static TYPE = new CategoryDefType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
