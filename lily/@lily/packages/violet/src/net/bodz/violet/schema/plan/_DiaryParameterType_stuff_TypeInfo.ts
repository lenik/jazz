import CoParameterTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoParameterTypeInfo";

import _DiaryParameterType_stuff_Validators from "./_DiaryParameterType_stuff_Validators";

export class _DiaryParameterType_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "diaryparm";

    readonly validators = new _DiaryParameterType_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryParameterType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _DiaryParameterType_stuff_TypeInfo;

export const _DiaryParameterType_stuff_TYPE = _DiaryParameterType_stuff_TypeInfo.INSTANCE;
