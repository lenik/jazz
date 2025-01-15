import type { long } from "skel01-core/src/lang/basetype";
import FavRecord from "lily-basic/src/net/bodz/lily/concrete/FavRecord";

import type TestQuestion from "./TestQuestion";
import _TestQuestionFav_stuff_TypeInfo from "./_TestQuestionFav_stuff_TypeInfo";

export class _TestQuestionFav_stuff extends FavRecord {

    static _typeInfo: _TestQuestionFav_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TestQuestionFav_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    testq: TestQuestion;
    testqId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _TestQuestionFav_stuff;
