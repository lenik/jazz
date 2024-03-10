import PersonAssetTypeInfo from "./PersonAssetTypeInfo";
import _PersonAsset_stuff from "./_PersonAsset_stuff";

export class PersonAsset extends _PersonAsset_stuff {

    static _typeInfo: PersonAssetTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PersonAssetTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonAsset;
