import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import CategoryDef from "./CategoryDef";
import SchemaDef from "./SchemaDef";
import _CategoryDef_stuff_TypeInfo from "./_CategoryDef_stuff_TypeInfo";

export class _CategoryDef_stuff extends CoEntity<integer> {
    static TYPE = new _CategoryDef_stuff_TypeInfo();

    id: integer;
    code?: string;
    depth: integer;
    refCount: integer;

    schema: SchemaDef;
    schemaId: integer;

    parent?: CategoryDef;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _CategoryDef_stuff;
