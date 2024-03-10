import type { int } from "@skeljs/core/src/lang/basetype";

import CoCategory from "../../concrete/CoCategory";

export class _ArticleCategory_stuff<this_t> extends CoCategory<this_t, int> {

    name?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleCategory_stuff;
