import type { BigDecimal, int, long } from "skel01-core/src/lang/basetype";
import CoMessage from "@lily/basic/src/net/bodz/lily/concrete/CoMessage";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import type TestPaper from "./TestPaper";
import _TestApply_stuff_TypeInfo from "./_TestApply_stuff_TypeInfo";

export class _TestApply_stuff extends CoMessage<long> {

    static _typeInfo: _TestApply_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TestApply_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    score?: BigDecimal;

    person?: Person;
    personId?: int;

    paper?: TestPaper;
    paperId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _TestApply_stuff;
