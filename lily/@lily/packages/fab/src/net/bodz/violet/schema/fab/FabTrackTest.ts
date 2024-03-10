import FabTrackTestTypeInfo from "./FabTrackTestTypeInfo";
import _FabTrackTest_stuff from "./_FabTrackTest_stuff";

export class FabTrackTest extends _FabTrackTest_stuff {

    static _typeInfo: FabTrackTestTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabTrackTestTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FabTrackTest;
