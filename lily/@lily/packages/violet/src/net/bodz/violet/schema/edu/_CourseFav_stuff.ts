import type { int } from "skel01-core/src/lang/basetype";
import FavRecord from "@lily/basic/src/net/bodz/lily/concrete/FavRecord";

import type Course from "./Course";
import _CourseFav_stuff_TypeInfo from "./_CourseFav_stuff_TypeInfo";

export class _CourseFav_stuff extends FavRecord {

    static _typeInfo: _CourseFav_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _CourseFav_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    course: Course;
    courseId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _CourseFav_stuff;
