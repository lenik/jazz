import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import _SchemaDef_stuff_TypeInfo from "./_SchemaDef_stuff_TypeInfo";

export class _SchemaDef_stuff extends CoEntity<int> {
    static _typeInfo: _SchemaDef_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _SchemaDef_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    code?: string;
    dummy?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _SchemaDef_stuff;
