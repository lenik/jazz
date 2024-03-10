import type { int } from "@skeljs/core/src/lang/basetype";
import CoCategory from "@lily/basic/src/net/bodz/lily/concrete/CoCategory";

export class _ShopCategory_stuff<this_t> extends CoCategory<this_t, int> {

    name?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _ShopCategory_stuff;
