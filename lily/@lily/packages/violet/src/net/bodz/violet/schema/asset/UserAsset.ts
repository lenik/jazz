import UserAssetTypeInfo from "./UserAssetTypeInfo";
import _UserAsset_stuff from "./_UserAsset_stuff";

export class UserAsset extends _UserAsset_stuff {

    static _typeInfo: UserAssetTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = UserAssetTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default UserAsset;
