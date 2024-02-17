
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { _SchemaDef_stuff_Type } from "./_SchemaDef_stuff_Type";

export class _SchemaDef_stuff extends CoEntity<Integer> {
    static TYPE = new _SchemaDef_stuff_Type();

    id: int;
    code?: string;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}
