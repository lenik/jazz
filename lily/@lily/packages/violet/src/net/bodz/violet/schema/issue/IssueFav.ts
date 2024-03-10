import IssueFavTypeInfo from "./IssueFavTypeInfo";
import _IssueFav_stuff from "./_IssueFav_stuff";

export class IssueFav extends _IssueFav_stuff {

    static _typeInfo: IssueFavTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = IssueFavTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default IssueFav;
