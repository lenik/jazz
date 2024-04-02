import type { int } from "@skeljs/core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type SchemaDef from "./SchemaDef";
import _PhaseDef_stuff_TypeInfo from "./_PhaseDef_stuff_TypeInfo";

export class _PhaseDef_stuff extends IdEntity<int> {

    static _typeInfo: _PhaseDef_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PhaseDef_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    code?: string;

    schema: SchemaDef;
    schemaId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _PhaseDef_stuff;
