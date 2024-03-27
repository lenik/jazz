import FabFnTypeInfo from "./FabFnTypeInfo";
import _FabFn_stuff from "./_FabFn_stuff";

export class FabFn extends _FabFn_stuff {

    static _typeInfo: FabFnTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabFnTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default FabFn;
