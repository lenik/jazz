import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type SchemaDef from "./SchemaDef";
import _FormDef_stuff_TypeInfo from "./_FormDef_stuff_TypeInfo";

export class _FormDef_stuff extends CoEntity<int> {
    static _typeInfo: _FormDef_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _FormDef_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    code?: string;
    subject?: string;
    rawText?: string;

    schema: SchemaDef;
    schemaId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FormDef_stuff;
