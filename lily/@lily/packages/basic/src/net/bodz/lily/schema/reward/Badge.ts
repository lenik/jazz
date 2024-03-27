import BadgeTypeInfo from "./BadgeTypeInfo";
import _Badge_stuff from "./_Badge_stuff";

export class Badge extends _Badge_stuff {

    static _typeInfo: BadgeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = BadgeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default Badge;
