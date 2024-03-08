import PhaseDefValidators from "./PhaseDefValidators";
import _PhaseDef_stuff_TypeInfo from "./_PhaseDef_stuff_TypeInfo";

export class PhaseDefTypeInfo extends _PhaseDef_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.PhaseDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Phase"; }

    validators = new PhaseDefValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PhaseDefTypeInfo;
