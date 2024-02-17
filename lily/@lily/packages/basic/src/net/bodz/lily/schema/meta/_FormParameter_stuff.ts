
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { FormDef } from "./FormDef";
import type { _FormParameter_stuff_Type } from "./_FormParameter_stuff_Type";

export class _FormParameter_stuff extends CoEntity<Integer> {
    static TYPE = new _FormParameter_stuff_Type();

    id: int;
    name?: string;
    value?: string;

    form: FormDef;
    formId: int;

    constructor(o: any) {
        super(o);
    }
}
