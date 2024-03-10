import CourseKitCategoryTypeInfo from "./CourseKitCategoryTypeInfo";
import _CourseKitCategory_stuff from "./_CourseKitCategory_stuff";

export class CourseKitCategory extends _CourseKitCategory_stuff<CourseKitCategory> {

    static _typeInfo: CourseKitCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CourseKitCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default CourseKitCategory;
