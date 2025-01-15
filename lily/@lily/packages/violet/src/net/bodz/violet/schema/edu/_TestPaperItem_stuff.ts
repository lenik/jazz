import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import CoImaged from "lily-basic/src/net/bodz/lily/concrete/CoImaged";

import type TestPaper from "./TestPaper";
import type TestQuestion from "./TestQuestion";
import _TestPaperItem_stuff_TypeInfo from "./_TestPaperItem_stuff_TypeInfo";

export class _TestPaperItem_stuff extends CoImaged<long> {

    static _typeInfo: _TestPaperItem_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TestPaperItem_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    score?: BigDecimal;

    question: TestQuestion;
    questionId: long;

    paper: TestPaper;
    paperId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _TestPaperItem_stuff;
