import Dim3d from "../art/Dim3d";
import RegionTypeInfo from "./RegionTypeInfo";
import _Region_stuff from "./_Region_stuff";

export class Region extends _Region_stuff<Region> {

    static _typeInfo: RegionTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = RegionTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    bbox?: Dim3d
    fullPath?: string
    position?: Dim3d

    constructor(o?: any) {
        super(o);
    }
}

export default Region;
