import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type Zone from "./Zone";
import type ZoneCategory from "./ZoneCategory";
import _Zone_stuff_TypeInfo from "./_Zone_stuff_TypeInfo";

export class _Zone_stuff extends CoEntity<integer> {
    static TYPE = new _Zone_stuff_TypeInfo();

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
