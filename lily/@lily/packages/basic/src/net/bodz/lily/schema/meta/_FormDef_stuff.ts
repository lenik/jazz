
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { SchemaDef } from "./SchemaDef";
import type { _FormDef_stuff_Type } from "./_FormDef_stuff_Type";

export class _FormDef_stuff extends CoEntity<Integer> {
    static TYPE = new _FormDef_stuff_Type();

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
