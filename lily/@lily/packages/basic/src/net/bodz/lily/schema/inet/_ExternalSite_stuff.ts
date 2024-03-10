import type { int } from "@skeljs/core/src/lang/basetype";

import CoNode from "../../concrete/CoNode";

export class _ExternalSite_stuff<this_t> extends CoNode<this_t, int> {

    urlfmt?: string;
    bonus: int;
    count: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ExternalSite_stuff;
