import VAppRequestApiTypeInfo from "./VAppRequestApiTypeInfo";
import _VAppRequestApi_stuff from "./_VAppRequestApi_stuff";

export class VAppRequestApi extends _VAppRequestApi_stuff {

    static _typeInfo: VAppRequestApiTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = VAppRequestApiTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default VAppRequestApi;
