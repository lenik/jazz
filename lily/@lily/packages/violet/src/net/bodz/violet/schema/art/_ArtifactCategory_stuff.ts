import type { int } from "skel01-core/src/lang/basetype";
import CoCategory from "lily-basic/src/net/bodz/lily/concrete/CoCategory";

export class _ArtifactCategory_stuff<this_t> extends CoCategory<this_t, int> {

    code: string;
    skufmt?: string;
    skufmtfull?: string;
    barfmt?: string;
    barfmtfull?: string;
    batchfmt?: string;
    serialfmt?: string;
    count: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArtifactCategory_stuff;
