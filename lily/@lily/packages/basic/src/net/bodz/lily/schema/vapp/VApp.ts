import VAppTypeInfo from "./VAppTypeInfo";
import _VApp_stuff from "./_VApp_stuff";

export class VApp extends _VApp_stuff {
    static _typeInfo: VAppTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new VAppTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApp;
