
import { * as validators } from "./PersonValidators";
import type { _FormDef_stuff } from "./_FormDef_stuff";

export class FormDef extends _FormDef_stuff {
    static TYPE = new FormDefType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
