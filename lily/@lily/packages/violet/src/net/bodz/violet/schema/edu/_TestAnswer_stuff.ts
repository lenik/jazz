import type { long } from "skel01-core/src/lang/basetype";
import CoMessage from "@lily/basic/src/net/bodz/lily/concrete/CoMessage";

import type TestQuestion from "./TestQuestion";
import _TestAnswer_stuff_TypeInfo from "./_TestAnswer_stuff_TypeInfo";

export class _TestAnswer_stuff extends CoMessage<long> {

    static _typeInfo: _TestAnswer_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TestAnswer_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    valid: boolean;

    question: TestQuestion;
    questionId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _TestAnswer_stuff;
