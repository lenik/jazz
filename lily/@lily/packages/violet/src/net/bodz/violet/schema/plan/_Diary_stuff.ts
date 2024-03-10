import type { int, long } from "@skeljs/core/src/lang/basetype";
import CoMessage from "@lily/basic/src/net/bodz/lily/concrete/CoMessage";

import type DiaryCategory from "./DiaryCategory";
import type DiaryPhase from "./DiaryPhase";
import _Diary_stuff_TypeInfo from "./_Diary_stuff_TypeInfo";

export class _Diary_stuff extends CoMessage<long> {

    static _typeInfo: _Diary_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Diary_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;
    value: int;

    phase?: DiaryPhase;
    phaseId?: int;

    category: DiaryCategory;
    categoryId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Diary_stuff;
