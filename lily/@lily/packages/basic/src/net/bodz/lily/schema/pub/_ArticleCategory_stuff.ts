
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { ArticleCategory } from "./ArticleCategory";
import type { _ArticleCategory_stuff_Type } from "./_ArticleCategory_stuff_Type";

export class _ArticleCategory_stuff extends CoEntity<Integer> {
    static TYPE = new _ArticleCategory_stuff_Type();

    id: int;
    name?: string;
    depth: int;
    refCount: int;

    parent?: ArticleCategory;
    parentId?: integer;

    constructor(o: any) {
        super(o);
    }
}
