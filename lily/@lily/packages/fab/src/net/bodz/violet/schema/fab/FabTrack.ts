import FabTrackTypeInfo from "./FabTrackTypeInfo";
import _FabTrack_stuff from "./_FabTrack_stuff";

export class FabTrack extends _FabTrack_stuff {

    static _typeInfo: FabTrackTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabTrackTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FabTrack;
