import TransportPhase from "./TransportPhase";
import TransportPhaseValidators from "./TransportPhaseValidators";
import _TransportPhase_stuff_TypeInfo from "./_TransportPhase_stuff_TypeInfo";

export class TransportPhaseTypeInfo extends _TransportPhase_stuff_TypeInfo {

    readonly validators = new TransportPhaseValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.tran.TransportPhase"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new TransportPhase();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TransportPhaseTypeInfo();

}

export default TransportPhaseTypeInfo;
