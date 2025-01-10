import type { int } from "skel01-core/src/lang/basetype";

import CoCategory from "../../concrete/CoCategory";

export class _ZoneCategory_stuff<this_t> extends CoCategory<this_t, int> {

    name?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _ZoneCategory_stuff;
