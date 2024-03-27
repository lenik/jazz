import RegionCategoryTypeInfo from "./RegionCategoryTypeInfo";
import _RegionCategory_stuff from "./_RegionCategory_stuff";

export class RegionCategory extends _RegionCategory_stuff<RegionCategory> {

    static _typeInfo: RegionCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = RegionCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default RegionCategory;
