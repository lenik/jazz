import type { double, int, long } from "@skeljs/core/src/lang/basetype";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";

import type TestApply from "./TestApply";
import type TestQuestion from "./TestQuestion";
import _TestApplyItem_stuff_TypeInfo from "./_TestApplyItem_stuff_TypeInfo";

export class _TestApplyItem_stuff extends CoEntity<long> {

    static _typeInfo: _TestApplyItem_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TestApplyItem_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: long;
    answer?: int;
    anstext?: string;
    score?: double;
    waittime?: double;

    question: TestQuestion;
    questionId: long;

    apply: TestApply;
    applyId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _TestApplyItem_stuff;
