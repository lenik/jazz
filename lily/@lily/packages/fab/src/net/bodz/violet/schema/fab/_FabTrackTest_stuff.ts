import type { int, long } from "@skeljs/core/src/lang/basetype";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";

import type FabStdTest from "./FabStdTest";
import type FabTrack from "./FabTrack";
import _FabTrackTest_stuff_TypeInfo from "./_FabTrackTest_stuff_TypeInfo";

export class _FabTrackTest_stuff extends CoEntity<long> {

    static _typeInfo: _FabTrackTest_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabTrackTest_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: long;
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
