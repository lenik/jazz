import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type SchemaDef from "./SchemaDef";
import _ParameterDef_stuff_TypeInfo from "./_ParameterDef_stuff_TypeInfo";

export class _ParameterDef_stuff extends CoEntity<int> {
    static _typeInfo: _ParameterDef_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _ParameterDef_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    code?: string;

    schema: SchemaDef;
    schemaId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ParameterDef_stuff;
