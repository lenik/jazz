import PlanFavTypeInfo from "./PlanFavTypeInfo";
import _PlanFav_stuff from "./_PlanFav_stuff";

export class PlanFav extends _PlanFav_stuff {

    static _typeInfo: PlanFavTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanFavTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PlanFav;
