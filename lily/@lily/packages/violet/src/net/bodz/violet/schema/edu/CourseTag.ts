import CourseTagTypeInfo from "./CourseTagTypeInfo";
import _CourseTag_stuff from "./_CourseTag_stuff";

export class CourseTag extends _CourseTag_stuff<CourseTag> {

    static _typeInfo: CourseTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CourseTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default CourseTag;
