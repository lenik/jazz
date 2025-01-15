import CoPhaseTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoPhaseTypeInfo";

import _StorePhase_stuff_Validators from "./_StorePhase_stuff_Validators";

export class _StorePhase_stuff_TypeInfo extends CoPhaseTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "storephase";

    readonly validators = new _StorePhase_stuff_Validators(this);

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

    static readonly INSTANCE = new _StorePhase_stuff_TypeInfo();

}

export default _StorePhase_stuff_TypeInfo;

export const _StorePhase_stuff_TYPE = _StorePhase_stuff_TypeInfo.INSTANCE;
