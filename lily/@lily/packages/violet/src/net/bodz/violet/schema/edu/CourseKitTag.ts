import CourseKitTagTypeInfo from "./CourseKitTagTypeInfo";
import _CourseKitTag_stuff from "./_CourseKitTag_stuff";

export class CourseKitTag extends _CourseKitTag_stuff<CourseKitTag> {

    static _typeInfo: CourseKitTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CourseKitTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default CourseKitTag;
