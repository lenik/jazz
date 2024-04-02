import PlanDoParameter from "./PlanDoParameter";
import PlanDoParameterValidators from "./PlanDoParameterValidators";
import _PlanDoParameter_stuff_TypeInfo from "./_PlanDoParameter_stuff_TypeInfo";

export class PlanDoParameterTypeInfo extends _PlanDoParameter_stuff_TypeInfo {

    readonly validators = new PlanDoParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanDoParameter"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PlanDoParameter();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanDoParameterTypeInfo();

}

export default PlanDoParameterTypeInfo;

export const PlanDoParameter_TYPE = PlanDoParameterTypeInfo.INSTANCE;
