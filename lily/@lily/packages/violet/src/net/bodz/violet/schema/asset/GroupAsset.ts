import GroupAssetTypeInfo from "./GroupAssetTypeInfo";
import _GroupAsset_stuff from "./_GroupAsset_stuff";

export class GroupAsset extends _GroupAsset_stuff {

    static _typeInfo: GroupAssetTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = GroupAssetTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default GroupAsset;
