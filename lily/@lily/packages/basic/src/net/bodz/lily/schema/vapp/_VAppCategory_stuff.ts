import type { int } from "@skeljs/core/src/lang/basetype";

import CoCategory from "../../concrete/CoCategory";

export class _VAppCategory_stuff<this_t> extends CoCategory<this_t, int> {

    name?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _VAppCategory_stuff;