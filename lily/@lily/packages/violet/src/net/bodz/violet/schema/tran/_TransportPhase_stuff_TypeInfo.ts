import CoPhaseTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoPhaseTypeInfo";

import _TransportPhase_stuff_Validators from "./_TransportPhase_stuff_Validators";

export class _TransportPhase_stuff_TypeInfo extends CoPhaseTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "tranphase";

    readonly validators = new _TransportPhase_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.tran.TransportPhase"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new _TransportPhase_stuff_TypeInfo();

}

export default _TransportPhase_stuff_TypeInfo;

export const _TransportPhase_stuff_TYPE = _TransportPhase_stuff_TypeInfo.INSTANCE;
