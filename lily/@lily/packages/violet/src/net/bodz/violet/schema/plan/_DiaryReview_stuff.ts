import type { long } from "@skeljs/core/src/lang/basetype";
import CoMessage from "@lily/basic/src/net/bodz/lily/concrete/CoMessage";

import type Diary from "./Diary";
import type DiaryReview from "./DiaryReview";
import _DiaryReview_stuff_TypeInfo from "./_DiaryReview_stuff_TypeInfo";

export class _DiaryReview_stuff extends CoMessage<long> {

    static _typeInfo: _DiaryReview_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _DiaryReview_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    formArguments?: string;

    diary: Diary;
    diaryId: long;

    parent?: DiaryReview;
    parentId?: long;

    constructor(o: any) {
        super(o);
    }
}

export default _DiaryReview_stuff;
