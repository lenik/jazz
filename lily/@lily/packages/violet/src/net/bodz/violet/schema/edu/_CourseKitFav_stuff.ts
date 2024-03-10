import type { int } from "@skeljs/core/src/lang/basetype";
import FavRecord from "@lily/basic/src/net/bodz/lily/concrete/FavRecord";

import type CourseKit from "./CourseKit";
import _CourseKitFav_stuff_TypeInfo from "./_CourseKitFav_stuff_TypeInfo";

export class _CourseKitFav_stuff extends FavRecord {

    static _typeInfo: _CourseKitFav_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _CourseKitFav_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    coursekit: CourseKit;
    coursekitId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _CourseKitFav_stuff;
