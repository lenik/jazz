import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import SchemaDef from "./SchemaDef";
import _ParameterDef_stuff_TypeInfo from "./_ParameterDef_stuff_TypeInfo";

export class _ParameterDef_stuff extends CoEntity<integer> {
    static TYPE = new _ParameterDef_stuff_TypeInfo();

    id: integer;
    code?: string;

    schema: SchemaDef;
    schemaId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _ParameterDef_stuff;
