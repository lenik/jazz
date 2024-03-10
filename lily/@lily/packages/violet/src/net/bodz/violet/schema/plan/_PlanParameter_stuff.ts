import type { int } from "@skeljs/core/src/lang/basetype";
import CoParameter from "@lily/basic/src/net/bodz/lily/concrete/CoParameter";

export class _PlanParameter_stuff<this_t> extends CoParameter<this_t> {

    name?: string;
    dummy?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _PlanParameter_stuff;
