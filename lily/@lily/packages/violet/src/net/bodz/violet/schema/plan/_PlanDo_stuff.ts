import type { int, long } from "@skeljs/core/src/lang/basetype";
import CoMessage from "@lily/basic/src/net/bodz/lily/concrete/CoMessage";

import type Plan from "./Plan";
import type PlanDo from "./PlanDo";
import _PlanDo_stuff_TypeInfo from "./_PlanDo_stuff_TypeInfo";

export class _PlanDo_stuff extends CoMessage<long> {

    static _typeInfo: _PlanDo_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PlanDo_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;
    voteCount: int;
    changes?: string[];

    plan: Plan;
    planId: long;

    parent?: PlanDo;
    parentId?: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PlanDo_stuff;
