import CoPhaseTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoPhaseTypeInfo";

import _DiaryPhase_stuff_Validators from "./_DiaryPhase_stuff_Validators";

export class _DiaryPhase_stuff_TypeInfo extends CoPhaseTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "diaryphase";

    readonly validators = new _DiaryPhase_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryPhase"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new _DiaryPhase_stuff_TypeInfo();

}

export default _DiaryPhase_stuff_TypeInfo;
