import PhaseDefTypeInfo from "./PhaseDefTypeInfo";
import _PhaseDef_stuff from "./_PhaseDef_stuff";

export class PhaseDef extends _PhaseDef_stuff {
    static TYPE = new PhaseDefTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PhaseDef;
