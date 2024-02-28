import ZoneCategoryTypeInfo from "./ZoneCategoryTypeInfo";
import _ZoneCategory_stuff from "./_ZoneCategory_stuff";

export class ZoneCategory extends _ZoneCategory_stuff {
    static _typeInfo: ZoneCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new ZoneCategoryTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ZoneCategory;
