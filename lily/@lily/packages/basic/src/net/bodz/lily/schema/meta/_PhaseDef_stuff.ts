import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import SchemaDef from "./SchemaDef";
import _PhaseDef_stuff_Type from "./_PhaseDef_stuff_Type";

export class _PhaseDef_stuff extends CoEntity<integer> {
    static TYPE = new _PhaseDef_stuff_Type();

    id: integer;
    code?: string;

    schema: SchemaDef;
    schemaId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _PhaseDef_stuff;
