import VApiTypeInfo from "./VApiTypeInfo";
import _VApi_stuff from "./_VApi_stuff";

export class VApi extends _VApi_stuff {

    static _typeInfo: VApiTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = VApiTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default VApi;
