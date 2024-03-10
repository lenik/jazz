import CourseTypeInfo from "./CourseTypeInfo";
import _Course_stuff from "./_Course_stuff";

export class Course extends _Course_stuff {

    static _typeInfo: CourseTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CourseTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Course;
