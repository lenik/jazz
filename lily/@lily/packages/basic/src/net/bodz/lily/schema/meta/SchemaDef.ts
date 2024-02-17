
import type { List } from "../../../../../java/util/List";
import { * as validators } from "./PersonValidators";
import type { _SchemaDef_stuff } from "./_SchemaDef_stuff";

export class SchemaDef extends _SchemaDef_stuff {
    static TYPE = new SchemaDefType();

    categories?: List
    parameters?: List
    phases?: List
    priorities?: List
    tagGroups?: List

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
