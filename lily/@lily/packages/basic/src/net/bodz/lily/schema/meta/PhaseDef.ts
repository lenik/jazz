import PhaseDefTypeInfo from "./PhaseDefTypeInfo";
import _PhaseDef_stuff from "./_PhaseDef_stuff";

export class PhaseDef extends _PhaseDef_stuff {

    static _typeInfo: PhaseDefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PhaseDefTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PhaseDef;
