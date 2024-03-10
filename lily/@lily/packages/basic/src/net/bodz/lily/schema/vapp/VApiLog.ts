import VApiLogTypeInfo from "./VApiLogTypeInfo";
import _VApiLog_stuff from "./_VApiLog_stuff";

export class VApiLog extends _VApiLog_stuff {

    static _typeInfo: VApiLogTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = VApiLogTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApiLog;
