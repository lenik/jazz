import UserBadgeTypeInfo from "./UserBadgeTypeInfo";
import _UserBadge_stuff from "./_UserBadge_stuff";

export class UserBadge extends _UserBadge_stuff {

    static _typeInfo: UserBadgeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = UserBadgeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default UserBadge;
