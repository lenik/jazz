import type { double, int, long } from "skel01-core/src/lang/basetype";
import CoMessage from "lily-basic/src/net/bodz/lily/concrete/CoMessage";

import type IssueCategory from "./IssueCategory";
import type IssuePhase from "./IssuePhase";
import _Issue_stuff_TypeInfo from "./_Issue_stuff_TypeInfo";

export class _Issue_stuff extends CoMessage<long> {

    static _typeInfo: _Issue_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Issue_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;
    readCount: int;
    voteCount: int;
    nlike: int;
    value: double;

    category: IssueCategory;
    categoryId: int;

    phase: IssuePhase;
    phaseId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Issue_stuff;
