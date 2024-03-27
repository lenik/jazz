import RegionTagTypeInfo from "./RegionTagTypeInfo";
import _RegionTag_stuff from "./_RegionTag_stuff";

export class RegionTag extends _RegionTag_stuff<RegionTag> {

    static _typeInfo: RegionTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = RegionTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default RegionTag;
