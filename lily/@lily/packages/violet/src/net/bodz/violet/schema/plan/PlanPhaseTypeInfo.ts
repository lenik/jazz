import PlanPhaseValidators from "./PlanPhaseValidators";
import _PlanPhase_stuff_TypeInfo from "./_PlanPhase_stuff_TypeInfo";

export class PlanPhaseTypeInfo extends _PlanPhase_stuff_TypeInfo {

    readonly validators = new PlanPhaseValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanPhase"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanPhaseTypeInfo();

}

export default PlanPhaseTypeInfo;
