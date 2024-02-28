import UserSecretTypeInfo from "./UserSecretTypeInfo";
import _UserSecret_stuff from "./_UserSecret_stuff";

export class UserSecret extends _UserSecret_stuff {
    static _typeInfo: UserSecretTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new UserSecretTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserSecret;
