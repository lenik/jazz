import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type CategoryDef from "./CategoryDef";
import type SchemaDef from "./SchemaDef";
import _CategoryDef_stuff_TypeInfo from "./_CategoryDef_stuff_TypeInfo";

export class _CategoryDef_stuff extends CoEntity<int> {
    static _typeInfo: _CategoryDef_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _CategoryDef_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    code?: string;
    depth: int;
    refCount: int;

    schema: SchemaDef;
    schemaId: int;

    parent?: CategoryDef;
    parentId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _CategoryDef_stuff;
