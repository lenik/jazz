import UserOtherIdTypeInfo from "./UserOtherIdTypeInfo";
import _UserOtherId_stuff from "./_UserOtherId_stuff";

export class UserOtherId extends _UserOtherId_stuff {
    static _typeInfo: UserOtherIdTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new UserOtherIdTypeInfo(); 
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserOtherId;
