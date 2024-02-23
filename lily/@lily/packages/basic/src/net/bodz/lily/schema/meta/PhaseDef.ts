import _PhaseDef_stuff from "./_PhaseDef_stuff";
import { _PhaseDef_stuff_Type } from "./_PhaseDef_stuff_Type";

export class PhaseDef extends _PhaseDef_stuff {
    static TYPE = new _PhaseDef_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PhaseDef;
