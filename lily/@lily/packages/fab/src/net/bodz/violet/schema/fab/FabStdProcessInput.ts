import FabStdProcessInputTypeInfo from "./FabStdProcessInputTypeInfo";
import _FabStdProcessInput_stuff from "./_FabStdProcessInput_stuff";

export class FabStdProcessInput extends _FabStdProcessInput_stuff {

    static _typeInfo: FabStdProcessInputTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabStdProcessInputTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FabStdProcessInput;
