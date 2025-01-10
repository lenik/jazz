import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import CoImaged from "@lily/basic/src/net/bodz/lily/concrete/CoImaged";

import type CourseCategory from "./CourseCategory";
import _Course_stuff_TypeInfo from "./_Course_stuff_TypeInfo";

export class _Course_stuff extends CoImaged<int> {

    static _typeInfo: _Course_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Course_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    favCount: int;
    voteCount: int;
    hateCount: int;
    credit: int;
    plugins?: JsonVariant;

    category?: CourseCategory;
    categoryId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Course_stuff;
