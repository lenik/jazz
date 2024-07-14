import PlanParameterType from "./PlanParameterType";
import PlanParameterTypeValidators from "./PlanParameterTypeValidators";
import _PlanParameterType_stuff_TypeInfo from "./_PlanParameterType_stuff_TypeInfo";

export class PlanParameterTypeTypeInfo extends _PlanParameterType_stuff_TypeInfo {

    readonly validators = new PlanParameterTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanParameterType"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PlanParameterType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanParameterTypeTypeInfo();

}

export default PlanParameterTypeTypeInfo;

export const PlanParameterType_TYPE = PlanParameterTypeTypeInfo.INSTANCE;
