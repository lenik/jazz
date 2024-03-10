import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import CoImaged from "@lily/basic/src/net/bodz/lily/concrete/CoImaged";

import type ArtifactModel from "../art/ArtifactModel";
import type FabFn from "./FabFn";
import type FabStdTest from "./FabStdTest";
import _FabStdProcess_stuff_TypeInfo from "./_FabStdProcess_stuff_TypeInfo";

export class _FabStdProcess_stuff extends CoImaged<int> {

    static _typeInfo: _FabStdProcess_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabStdProcess_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    valid: boolean;
    validSince?: ZonedDateTime;
    validUntil?: ZonedDateTime;
    duration: int;
    strict: boolean;

    output: ArtifactModel;
    outputId: int;

    test?: FabStdTest;
    testId?: int;

    function?: FabFn;
    functionId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FabStdProcess_stuff;
