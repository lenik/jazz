import VAppRequestApiTypeInfo from "./VAppRequestApiTypeInfo";
import _VAppRequestApi_stuff from "./_VAppRequestApi_stuff";

export class VAppRequestApi extends _VAppRequestApi_stuff {
    static _typeInfo: VAppRequestApiTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new VAppRequestApiTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppRequestApi;
