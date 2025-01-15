import CoPhaseTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoPhaseTypeInfo";

import _SalesPhase_stuff_Validators from "./_SalesPhase_stuff_Validators";

export class _SalesPhase_stuff_TypeInfo extends CoPhaseTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "salephase";

    readonly validators = new _SalesPhase_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.SalesPhase"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new _SalesPhase_stuff_TypeInfo();

}

export default _SalesPhase_stuff_TypeInfo;

export const _SalesPhase_stuff_TYPE = _SalesPhase_stuff_TypeInfo.INSTANCE;
