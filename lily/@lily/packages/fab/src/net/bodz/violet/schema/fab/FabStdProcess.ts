import FabStdProcessTypeInfo from "./FabStdProcessTypeInfo";
import _FabStdProcess_stuff from "./_FabStdProcess_stuff";

export class FabStdProcess extends _FabStdProcess_stuff {

    static _typeInfo: FabStdProcessTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabStdProcessTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default FabStdProcess;
