import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import SchemaDef from "./SchemaDef";
import _FormDef_stuff_TypeInfo from "./_FormDef_stuff_TypeInfo";

export class _FormDef_stuff extends CoEntity<integer> {
    static TYPE = new _FormDef_stuff_TypeInfo();

    id: integer;
    code?: string;
    subject?: string;
    rawText?: string;

    schema: SchemaDef;
    schemaId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _FormDef_stuff;
