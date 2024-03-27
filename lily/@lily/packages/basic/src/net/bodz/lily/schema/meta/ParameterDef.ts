import ParameterDefTypeInfo from "./ParameterDefTypeInfo";
import _ParameterDef_stuff from "./_ParameterDef_stuff";

export class ParameterDef extends _ParameterDef_stuff<ParameterDef> {

    static _typeInfo: ParameterDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ParameterDefTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ParameterDef;
