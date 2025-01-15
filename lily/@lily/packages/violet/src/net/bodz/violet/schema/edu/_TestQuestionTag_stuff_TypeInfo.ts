import CoTagTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoTagTypeInfo";

import _TestQuestionTag_stuff_Validators from "./_TestQuestionTag_stuff_Validators";

export class _TestQuestionTag_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "testqtag";

    readonly validators = new _TestQuestionTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestQuestionTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _TestQuestionTag_stuff_TypeInfo;

export const _TestQuestionTag_stuff_TYPE = _TestQuestionTag_stuff_TypeInfo.INSTANCE;
