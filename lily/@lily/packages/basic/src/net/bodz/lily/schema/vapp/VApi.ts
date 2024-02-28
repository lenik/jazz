import VApiTypeInfo from "./VApiTypeInfo";
import _VApi_stuff from "./_VApi_stuff";

export class VApi extends _VApi_stuff {
    static _typeInfo: VApiTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new VApiTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApi;
