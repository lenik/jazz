import PlanParameterValidators from "./PlanParameterValidators";
import _PlanParameter_stuff_TypeInfo from "./_PlanParameter_stuff_TypeInfo";

export class PlanParameterTypeInfo extends _PlanParameter_stuff_TypeInfo {

    readonly validators = new PlanParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanParameterTypeInfo();

}

export default PlanParameterTypeInfo;
