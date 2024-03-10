import type { int } from "@skeljs/core/src/lang/basetype";

import CoCategory from "../../concrete/CoCategory";
import type SchemaDef from "./SchemaDef";

export class _CategoryDef_stuff<this_t> extends CoCategory<this_t, int> {

    code?: string;

    schema: SchemaDef;
    schemaId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _CategoryDef_stuff;
