import CourseFavTypeInfo from "./CourseFavTypeInfo";
import _CourseFav_stuff from "./_CourseFav_stuff";

export class CourseFav extends _CourseFav_stuff {

    static _typeInfo: CourseFavTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CourseFavTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default CourseFav;
