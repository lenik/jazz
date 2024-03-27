import CourseKitTypeInfo from "./CourseKitTypeInfo";
import _CourseKit_stuff from "./_CourseKit_stuff";

export class CourseKit extends _CourseKit_stuff {

    static _typeInfo: CourseKitTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CourseKitTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default CourseKit;
