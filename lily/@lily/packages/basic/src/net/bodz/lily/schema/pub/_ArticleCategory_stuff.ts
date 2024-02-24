import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type ArticleCategory from "./ArticleCategory";
import _ArticleCategory_stuff_TypeInfo from "./_ArticleCategory_stuff_TypeInfo";

export class _ArticleCategory_stuff extends CoEntity<integer> {
    static TYPE = new _ArticleCategory_stuff_TypeInfo();

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
