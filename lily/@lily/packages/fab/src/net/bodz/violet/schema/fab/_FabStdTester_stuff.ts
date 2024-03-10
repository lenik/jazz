import type { int } from "@skeljs/core/src/lang/basetype";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";

import _FabStdTester_stuff_TypeInfo from "./_FabStdTester_stuff_TypeInfo";

export class _FabStdTester_stuff extends CoEntity<int> {

    static _typeInfo: _FabStdTester_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabStdTester_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: int;
    cmdline?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _FabStdTester_stuff;
