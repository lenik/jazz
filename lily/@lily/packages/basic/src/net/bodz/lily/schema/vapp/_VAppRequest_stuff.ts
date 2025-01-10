import type { int } from "skel01-core/src/lang/basetype";

import CoMessage from "../../concrete/CoMessage";
import _VAppRequest_stuff_TypeInfo from "./_VAppRequest_stuff_TypeInfo";

export class _VAppRequest_stuff extends CoMessage<int> {

    static _typeInfo: _VAppRequest_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _VAppRequest_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    code?: string;
    formArguments?: string;
    dummy?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _VAppRequest_stuff;
