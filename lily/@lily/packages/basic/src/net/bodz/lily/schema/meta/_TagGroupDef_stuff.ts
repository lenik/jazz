import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import SchemaDef from "./SchemaDef";
import _TagGroupDef_stuff_TypeInfo from "./_TagGroupDef_stuff_TypeInfo";

export class _TagGroupDef_stuff extends CoEntity<integer> {
    static TYPE = new _TagGroupDef_stuff_TypeInfo();

    id: integer;
    code?: string;
    forTopic: boolean;
    forReply: boolean;

    schema: SchemaDef;
    schemaId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _TagGroupDef_stuff;
