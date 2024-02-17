
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { SchemaDef } from "./SchemaDef";
import type { _TagGroupDef_stuff_Type } from "./_TagGroupDef_stuff_Type";

export class _TagGroupDef_stuff extends CoEntity<Integer> {
    static TYPE = new _TagGroupDef_stuff_Type();

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
