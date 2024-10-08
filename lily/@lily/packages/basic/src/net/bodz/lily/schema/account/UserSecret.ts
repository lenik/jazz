import UserSecretTypeInfo from "./UserSecretTypeInfo";
import _UserSecret_stuff from "./_UserSecret_stuff";

export class UserSecret extends _UserSecret_stuff {

    static _typeInfo: UserSecretTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = UserSecretTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default UserSecret;
