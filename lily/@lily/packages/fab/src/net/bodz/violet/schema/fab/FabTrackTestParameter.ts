import FabTrackTestParameterTypeInfo from "./FabTrackTestParameterTypeInfo";
import _FabTrackTestParameter_stuff from "./_FabTrackTestParameter_stuff";

export class FabTrackTestParameter extends _FabTrackTestParameter_stuff {

    static _typeInfo: FabTrackTestParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabTrackTestParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default FabTrackTestParameter;
