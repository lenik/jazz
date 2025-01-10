import type { int, long } from "skel01-core/src/lang/basetype";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";

import type FabStdTest from "./FabStdTest";
import type FabTrack from "./FabTrack";
import _FabTrackTest_stuff_TypeInfo from "./_FabTrackTest_stuff_TypeInfo";

export class _FabTrackTest_stuff extends IdEntity<long> {

    static _typeInfo: _FabTrackTest_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabTrackTest_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    valid: boolean;

    track: FabTrack;
    trackId: long;

    standard: FabStdTest;
    standardId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FabTrackTest_stuff;
