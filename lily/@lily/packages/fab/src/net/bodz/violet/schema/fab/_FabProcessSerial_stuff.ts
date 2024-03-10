import type { long } from "@skeljs/core/src/lang/basetype";
import CoEntity from "@lily/basic/src/net/bodz/lily/concrete/CoEntity";

import type FabProcess from "./FabProcess";
import _FabProcessSerial_stuff_TypeInfo from "./_FabProcessSerial_stuff_TypeInfo";

export class _FabProcessSerial_stuff extends CoEntity<long> {

    static _typeInfo: _FabProcessSerial_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabProcessSerial_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: long;
    serial: string;

    process: FabProcess;
    processId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _FabProcessSerial_stuff;
