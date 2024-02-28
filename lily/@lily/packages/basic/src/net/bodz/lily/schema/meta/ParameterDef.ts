import ParameterDefTypeInfo from "./ParameterDefTypeInfo";
import _ParameterDef_stuff from "./_ParameterDef_stuff";

export class ParameterDef extends _ParameterDef_stuff {
    static _typeInfo: ParameterDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new ParameterDefTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ParameterDef;
