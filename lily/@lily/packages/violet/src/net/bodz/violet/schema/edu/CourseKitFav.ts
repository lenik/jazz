import CourseKitFavTypeInfo from "./CourseKitFavTypeInfo";
import _CourseKitFav_stuff from "./_CourseKitFav_stuff";

export class CourseKitFav extends _CourseKitFav_stuff {

    static _typeInfo: CourseKitFavTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CourseKitFavTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default CourseKitFav;
