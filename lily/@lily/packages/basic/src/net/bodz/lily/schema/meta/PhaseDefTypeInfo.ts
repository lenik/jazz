import PhaseDef from "./PhaseDef";
import PhaseDefValidators from "./PhaseDefValidators";
import _PhaseDef_stuff_TypeInfo from "./_PhaseDef_stuff_TypeInfo";

export class PhaseDefTypeInfo extends _PhaseDef_stuff_TypeInfo {

    readonly validators = new PhaseDefValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.PhaseDef"; }
    get icon() { return "fa-tag"; }
    get description() { return ""; }

    override create() {
        return new PhaseDef();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PhaseDefTypeInfo();

}

export default PhaseDefTypeInfo;

export const PhaseDef_TYPE = PhaseDefTypeInfo.INSTANCE;
