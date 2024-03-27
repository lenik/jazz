import SalesPhase from "./SalesPhase";
import SalesPhaseValidators from "./SalesPhaseValidators";
import _SalesPhase_stuff_TypeInfo from "./_SalesPhase_stuff_TypeInfo";

export class SalesPhaseTypeInfo extends _SalesPhase_stuff_TypeInfo {

    readonly validators = new SalesPhaseValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.SalesPhase"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new SalesPhase();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new SalesPhaseTypeInfo();

}

export default SalesPhaseTypeInfo;
