import OrgAssetTypeInfo from "./OrgAssetTypeInfo";
import _OrgAsset_stuff from "./_OrgAsset_stuff";

export class OrgAsset extends _OrgAsset_stuff {

    static _typeInfo: OrgAssetTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = OrgAssetTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default OrgAsset;
