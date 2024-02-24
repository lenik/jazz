import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type CategoryDef from "./CategoryDef";
import type SchemaDef from "./SchemaDef";
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
