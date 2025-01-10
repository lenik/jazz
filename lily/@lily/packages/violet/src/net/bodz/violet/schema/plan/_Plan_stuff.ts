import type { double, int, long } from "skel01-core/src/lang/basetype";
import CoMessage from "@lily/basic/src/net/bodz/lily/concrete/CoMessage";

import type PlanCategory from "./PlanCategory";
import type PlanPhase from "./PlanPhase";
import _Plan_stuff_TypeInfo from "./_Plan_stuff_TypeInfo";

export class _Plan_stuff extends CoMessage<long> {

    static _typeInfo: _Plan_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Plan_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;
    readCount: int;
    voteCount: int;
    nlike: int;
    value: double;

    category: PlanCategory;
    categoryId: int;

    phase: PlanPhase;
    phaseId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Plan_stuff;
