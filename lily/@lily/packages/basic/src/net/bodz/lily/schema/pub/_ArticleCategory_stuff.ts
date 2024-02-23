import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import ArticleCategory from "./ArticleCategory";
import _ArticleCategory_stuff_Type from "./_ArticleCategory_stuff_Type";

export class _ArticleCategory_stuff extends CoEntity<integer> {
    static TYPE = new _ArticleCategory_stuff_Type();

    id: integer;
    name?: string;
    depth: integer;
    refCount: integer;

    parent?: ArticleCategory;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleCategory_stuff;
