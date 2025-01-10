import type { int } from "skel01-core/src/lang/basetype";

import CoCode from "../../concrete/CoCode";

export class _SchemaDef_stuff<this_t> extends CoCode<this_t> {

    dummy?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _SchemaDef_stuff;
