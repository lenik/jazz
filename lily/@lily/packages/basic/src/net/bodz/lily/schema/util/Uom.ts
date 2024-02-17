
import { * as validators } from "./PersonValidators";
import type { _Uom_stuff } from "./_Uom_stuff";

export class Uom extends _Uom_stuff {
    static TYPE = new UomType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
