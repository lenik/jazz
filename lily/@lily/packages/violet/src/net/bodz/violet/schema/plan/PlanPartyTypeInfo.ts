import PlanParty from "./PlanParty";
import PlanPartyValidators from "./PlanPartyValidators";
import _PlanParty_stuff_TypeInfo from "./_PlanParty_stuff_TypeInfo";

export class PlanPartyTypeInfo extends _PlanParty_stuff_TypeInfo {

    readonly validators = new PlanPartyValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanParty"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PlanParty();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PlanPartyTypeInfo();

}

export default PlanPartyTypeInfo;

export const PlanParty_TYPE = PlanPartyTypeInfo.INSTANCE;
