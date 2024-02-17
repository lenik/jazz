
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { SchemaDef } from "./SchemaDef";
import type { _PhaseDef_stuff_Type } from "./_PhaseDef_stuff_Type";

export class _PhaseDef_stuff extends CoEntity<Integer> {
    static TYPE = new _PhaseDef_stuff_Type();

    id: int;
    code?: string;

    schema: SchemaDef;
    schemaId: int;

    constructor(o: any) {
        super(o);
    }
}
