import StdParameterTypeInfo from "./StdParameterTypeInfo";
import _StdParameter_stuff from "./_StdParameter_stuff";

export class StdParameter extends _StdParameter_stuff<StdParameter> {

    static _typeInfo: StdParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = StdParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default StdParameter;
