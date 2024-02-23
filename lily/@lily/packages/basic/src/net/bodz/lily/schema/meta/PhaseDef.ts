import _PhaseDef_stuff from "./_PhaseDef_stuff";
import { _PhaseDef_stuffTypeInfo } from "./_PhaseDef_stuffTypeInfo";

export class PhaseDef extends _PhaseDef_stuff {
    static TYPE = new _PhaseDef_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PhaseDef;
