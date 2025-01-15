import type { int, long } from "skel01-core/src/lang/basetype";
import IdEntity from "lily-basic/src/net/bodz/lily/concrete/IdEntity";

import type FabStdTestParameter from "./FabStdTestParameter";
import type FabStdTester from "./FabStdTester";
import type FabTrackTest from "./FabTrackTest";
import _FabTrackTestParameter_stuff_TypeInfo from "./_FabTrackTestParameter_stuff_TypeInfo";

export class _FabTrackTestParameter_stuff extends IdEntity<long> {

    static _typeInfo: _FabTrackTestParameter_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabTrackTestParameter_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    actual: string;
    valid: boolean;

    parameter: FabStdTestParameter;
    parameterId: int;

    tester?: FabStdTester;
    testerId?: int;

    test: FabTrackTest;
    testId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _FabTrackTestParameter_stuff;
