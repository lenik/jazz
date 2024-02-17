
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { CategoryDef } from "./CategoryDef";
import type { SchemaDef } from "./SchemaDef";
import type { _CategoryDef_stuff_Type } from "./_CategoryDef_stuff_Type";

export class _CategoryDef_stuff extends CoEntity<Integer> {
    static TYPE = new _CategoryDef_stuff_Type();

    id: int;
    code?: string;
    depth: int;
    refCount: int;

    schema: SchemaDef;
    schemaId: int;

    parent?: CategoryDef;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}
