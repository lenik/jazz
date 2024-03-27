import RegionLevelTypeInfo from "./RegionLevelTypeInfo";
import _RegionLevel_stuff from "./_RegionLevel_stuff";

export class RegionLevel extends _RegionLevel_stuff<RegionLevel> {

    static _typeInfo: RegionLevelTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = RegionLevelTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default RegionLevel;
