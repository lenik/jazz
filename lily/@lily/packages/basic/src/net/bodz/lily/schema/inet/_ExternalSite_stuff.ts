import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type ExternalSite from "./ExternalSite";
import _ExternalSite_stuff_TypeInfo from "./_ExternalSite_stuff_TypeInfo";

export class _ExternalSite_stuff extends CoEntity<integer> {
    static TYPE = new _ExternalSite_stuff_TypeInfo();

    id: integer;
    depth: integer;
    urlfmt?: string;
    bonus: integer;
    count: integer;

    parent?: ExternalSite;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _ExternalSite_stuff;
