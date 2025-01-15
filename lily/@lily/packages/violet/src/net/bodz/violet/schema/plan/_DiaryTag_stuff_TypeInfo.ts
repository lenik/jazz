import CoTagTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoTagTypeInfo";

import _DiaryTag_stuff_Validators from "./_DiaryTag_stuff_Validators";

export class _DiaryTag_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "diarytag";

    readonly validators = new _DiaryTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _DiaryTag_stuff_TypeInfo;

export const _DiaryTag_stuff_TYPE = _DiaryTag_stuff_TypeInfo.INSTANCE;
