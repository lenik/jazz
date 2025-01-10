import type { int } from "skel01-core/src/lang/basetype";
import IdEntity from "@lily/basic/src/net/bodz/lily/concrete/IdEntity";

import _FabStdTester_stuff_TypeInfo from "./_FabStdTester_stuff_TypeInfo";

export class _FabStdTester_stuff extends IdEntity<int> {

    static _typeInfo: _FabStdTester_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabStdTester_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    cmdline?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _FabStdTester_stuff;
