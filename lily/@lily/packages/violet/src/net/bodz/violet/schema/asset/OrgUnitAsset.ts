import OrgUnitAssetTypeInfo from "./OrgUnitAssetTypeInfo";
import _OrgUnitAsset_stuff from "./_OrgUnitAsset_stuff";

export class OrgUnitAsset extends _OrgUnitAsset_stuff {

    static _typeInfo: OrgUnitAssetTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = OrgUnitAssetTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default OrgUnitAsset;
