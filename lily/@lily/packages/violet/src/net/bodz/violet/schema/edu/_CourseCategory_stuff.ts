import type { int } from "skel01-core/src/lang/basetype";
import CoCategory from "@lily/basic/src/net/bodz/lily/concrete/CoCategory";

export class _CourseCategory_stuff<this_t> extends CoCategory<this_t, int> {

    name?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _CourseCategory_stuff;
