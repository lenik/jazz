
import { * as validators } from "./PersonValidators";
import type { _VAppCat_stuff } from "./_VAppCat_stuff";

export class VAppCat extends _VAppCat_stuff {
    static TYPE = new VAppCatType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
