import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";

import type FabStdTest from "./FabStdTest";
import _FabStdTestParameter_stuff_TypeInfo from "./_FabStdTestParameter_stuff_TypeInfo";

export class _FabStdTestParameter_stuff extends CoEntity<int> {

    static _typeInfo: _FabStdTestParameter_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabStdTestParameter_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: int;
    required: boolean;
    properties?: JsonVariant;
    expected?: string;

    test: FabStdTest;
    testId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FabStdTestParameter_stuff;