import UserTypeTypeInfo from "./UserTypeTypeInfo";
import _UserType_stuff from "./_UserType_stuff";

export class UserType extends _UserType_stuff {

    static _typeInfo: UserTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = UserTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default UserType;
