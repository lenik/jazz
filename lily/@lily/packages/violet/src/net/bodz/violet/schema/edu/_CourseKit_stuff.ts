import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import CoImaged from "@lily/basic/src/net/bodz/lily/concrete/CoImaged";

import type Course from "./Course";
import type CourseKitCategory from "./CourseKitCategory";
import _CourseKit_stuff_TypeInfo from "./_CourseKit_stuff_TypeInfo";

export class _CourseKit_stuff extends CoImaged<int> {

    static _typeInfo: _CourseKit_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _CourseKit_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    favCount: int;
    voteCount: int;
    hateCount: int;
    dummy?: JsonVariant;

    category: CourseKitCategory;
    categoryId: int;

    course: Course;
    courseId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _CourseKit_stuff;
