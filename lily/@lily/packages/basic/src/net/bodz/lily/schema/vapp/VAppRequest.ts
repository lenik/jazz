import VAppRequestTypeInfo from "./VAppRequestTypeInfo";
import _VAppRequest_stuff from "./_VAppRequest_stuff";

export class VAppRequest extends _VAppRequest_stuff {

    static _typeInfo: VAppRequestTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = VAppRequestTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default VAppRequest;
