import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type SchemaDef from "./SchemaDef";
import _TagGroupDef_stuff_TypeInfo from "./_TagGroupDef_stuff_TypeInfo";

export class _TagGroupDef_stuff extends CoEntity<int> {
    static _typeInfo: _TagGroupDef_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _TagGroupDef_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    code?: string;
    forTopic: boolean;
    forReply: boolean;

    schema: SchemaDef;
    schemaId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _TagGroupDef_stuff;
