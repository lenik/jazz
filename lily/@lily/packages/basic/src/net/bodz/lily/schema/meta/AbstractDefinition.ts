import type { int } from "@skeljs/core/src/lang/basetype";
import CoCode from "../../concrete/CoCode";
import type SchemaDef from "./SchemaDef";

export class AbstractDefinition<This> extends CoCode<This> {

    schema?: SchemaDef
    schemaId: int

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }

}

export default AbstractDefinition;
