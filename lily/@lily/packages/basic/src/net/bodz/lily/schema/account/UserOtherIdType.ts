import UserOtherIdTypeTypeInfo from "./UserOtherIdTypeTypeInfo";
import _UserOtherIdType_stuff from "./_UserOtherIdType_stuff";

export class UserOtherIdType extends _UserOtherIdType_stuff {

    static _typeInfo: UserOtherIdTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = UserOtherIdTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserOtherIdType;
