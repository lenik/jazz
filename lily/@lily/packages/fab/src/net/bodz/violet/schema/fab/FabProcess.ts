import FabProcessTypeInfo from "./FabProcessTypeInfo";
import _FabProcess_stuff from "./_FabProcess_stuff";

export class FabProcess extends _FabProcess_stuff {

    static _typeInfo: FabProcessTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabProcessTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default FabProcess;
