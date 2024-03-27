import PlanCategory from "./PlanCategory";
import PlanCategoryValidators from "./PlanCategoryValidators";
import _PlanCategory_stuff_TypeInfo from "./_PlanCategory_stuff_TypeInfo";

export class PlanCategoryTypeInfo extends _PlanCategory_stuff_TypeInfo {

    readonly validators = new PlanCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanCategory"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PlanCategory();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanCategoryTypeInfo();

}

export default PlanCategoryTypeInfo;
