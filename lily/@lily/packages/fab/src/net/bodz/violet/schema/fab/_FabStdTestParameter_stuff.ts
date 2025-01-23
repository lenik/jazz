import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import IdEntity from "lily-basic/src/net/bodz/lily/concrete/IdEntity";

import type FabStdTest from "./FabStdTest";
import _FabStdTestParameter_stuff_TypeInfo from "./_FabStdTestParameter_stuff_TypeInfo";

export class _FabStdTestParameter_stuff extends IdEntity<int> {

    static _typeInfo: _FabStdTestParameter_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabStdTestParameter_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    required: boolean;
    properties?: JsonVariant;
    files?: JsonVariant;
    expected?: string;

    test: FabStdTest;
    testId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FabStdTestParameter_stuff;
