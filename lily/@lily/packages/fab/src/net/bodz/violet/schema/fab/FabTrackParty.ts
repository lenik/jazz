import FabTrackPartyTypeInfo from "./FabTrackPartyTypeInfo";
import _FabTrackParty_stuff from "./_FabTrackParty_stuff";

export class FabTrackParty extends _FabTrackParty_stuff {

    static _typeInfo: FabTrackPartyTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabTrackPartyTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FabTrackParty;
