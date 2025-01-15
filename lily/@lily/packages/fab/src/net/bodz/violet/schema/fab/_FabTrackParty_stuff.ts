import type { int, long } from "skel01-core/src/lang/basetype";
import IdEntity from "lily-basic/src/net/bodz/lily/concrete/IdEntity";
import type Person from "lily-basic/src/net/bodz/lily/schema/contact/Person";

import type FabTrack from "./FabTrack";
import _FabTrackParty_stuff_TypeInfo from "./_FabTrackParty_stuff_TypeInfo";

export class _FabTrackParty_stuff extends IdEntity<long> {

    static _typeInfo: _FabTrackParty_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabTrackParty_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    role?: string;

    person: Person;
    personId: int;

    track: FabTrack;
    trackId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _FabTrackParty_stuff;
