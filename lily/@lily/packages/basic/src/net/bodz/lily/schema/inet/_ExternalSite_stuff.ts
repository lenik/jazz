
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { ExternalSite } from "./ExternalSite";
import type { _ExternalSite_stuff_Type } from "./_ExternalSite_stuff_Type";

export class _ExternalSite_stuff extends CoEntity<Integer> {
    static TYPE = new _ExternalSite_stuff_Type();

    id: int;
    depth: int;
    urlfmt?: string;
    bonus: int;
    count: int;

    parent?: ExternalSite;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}
