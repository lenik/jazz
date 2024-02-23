import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import Zone from "./Zone";
import ZoneCategory from "./ZoneCategory";
import _Zone_stuff_Type from "./_Zone_stuff_Type";

export class _Zone_stuff extends CoEntity<integer> {
    static TYPE = new _Zone_stuff_Type();

    id: integer;
    code?: string;
    country?: string;
    depth: integer;
    telCode?: string;
    postCode?: string;
    data?: any;

    parent?: Zone;
    parentId?: integer;

    category: ZoneCategory;
    categoryId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _Zone_stuff;
