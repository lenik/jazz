import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import PostCategory from "./PostCategory";
import _PostCategory_stuff_TypeInfo from "./_PostCategory_stuff_TypeInfo";

export class _PostCategory_stuff extends CoEntity<integer> {
    static TYPE = new _PostCategory_stuff_TypeInfo();

    id: integer;
    name?: string;
    depth: integer;
    refCount: integer;

    parent?: PostCategory;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _PostCategory_stuff;
