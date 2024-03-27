import AssetTypeInfo from "./AssetTypeInfo";
import _Asset_stuff from "./_Asset_stuff";

export class Asset extends _Asset_stuff {

    static _typeInfo: AssetTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = AssetTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default Asset;
