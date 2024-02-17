
import { * as validators } from "./PersonValidators";
import type { _ParameterDef_stuff } from "./_ParameterDef_stuff";

export class ParameterDef extends _ParameterDef_stuff {
    static TYPE = new ParameterDefType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
