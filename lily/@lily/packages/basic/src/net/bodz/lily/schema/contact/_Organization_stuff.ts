import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";

import Party from "./Party";
import _Organization_stuff_TypeInfo from "./_Organization_stuff_TypeInfo";

export class _Organization_stuff extends Party {
    static _typeInfo: _Organization_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _Organization_stuff_TypeInfo();
        return this._typeInfo;
    }

    properties?: JsonVariant;
    roleCount: int;
    bankCount: int;
    size: int;
    taxId?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _Organization_stuff;
