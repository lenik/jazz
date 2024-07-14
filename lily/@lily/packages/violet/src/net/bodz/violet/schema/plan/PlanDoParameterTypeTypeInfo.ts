import PlanDoParameterType from "./PlanDoParameterType";
import PlanDoParameterTypeValidators from "./PlanDoParameterTypeValidators";
import _PlanDoParameterType_stuff_TypeInfo from "./_PlanDoParameterType_stuff_TypeInfo";

export class PlanDoParameterTypeTypeInfo extends _PlanDoParameterType_stuff_TypeInfo {

    readonly validators = new PlanDoParameterTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanDoParameterType"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PlanDoParameterType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanDoParameterTypeTypeInfo();

}

export default PlanDoParameterTypeTypeInfo;

export const PlanDoParameterType_TYPE = PlanDoParameterTypeTypeInfo.INSTANCE;
