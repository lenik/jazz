import FabStdTestParameterTypeInfo from "./FabStdTestParameterTypeInfo";
import _FabStdTestParameter_stuff from "./_FabStdTestParameter_stuff";

export class FabStdTestParameter extends _FabStdTestParameter_stuff {

    static _typeInfo: FabStdTestParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabStdTestParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default FabStdTestParameter;
