import StorePhaseValidators from "./StorePhaseValidators";
import _StorePhase_stuff_TypeInfo from "./_StorePhase_stuff_TypeInfo";

export class StorePhaseTypeInfo extends _StorePhase_stuff_TypeInfo {

    readonly validators = new StorePhaseValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.StorePhase"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new StorePhaseTypeInfo();

}

export default StorePhaseTypeInfo;
