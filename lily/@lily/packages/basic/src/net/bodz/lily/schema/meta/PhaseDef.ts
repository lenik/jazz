
import { * as validators } from "./PersonValidators";
import type { _PhaseDef_stuff } from "./_PhaseDef_stuff";

export class PhaseDef extends _PhaseDef_stuff {
    static TYPE = new PhaseDefType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
