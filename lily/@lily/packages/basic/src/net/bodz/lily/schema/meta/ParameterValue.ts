import ParameterValueTypeInfo from "./ParameterValueTypeInfo";
import _ParameterValue_stuff from "./_ParameterValue_stuff";

export class ParameterValue extends _ParameterValue_stuff<ParameterValue> {

    static _typeInfo: ParameterValueTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ParameterValueTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ParameterValue;
