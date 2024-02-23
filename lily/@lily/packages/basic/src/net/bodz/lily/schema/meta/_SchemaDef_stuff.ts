import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import _SchemaDef_stuff_Type from "./_SchemaDef_stuff_Type";

export class _SchemaDef_stuff extends CoEntity<integer> {
    static TYPE = new _SchemaDef_stuff_Type();

    id: integer;
    code?: string;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _SchemaDef_stuff;
