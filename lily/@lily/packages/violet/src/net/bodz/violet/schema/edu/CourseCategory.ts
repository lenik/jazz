import CourseCategoryTypeInfo from "./CourseCategoryTypeInfo";
import _CourseCategory_stuff from "./_CourseCategory_stuff";

export class CourseCategory extends _CourseCategory_stuff<CourseCategory> {

    static _typeInfo: CourseCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CourseCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default CourseCategory;
