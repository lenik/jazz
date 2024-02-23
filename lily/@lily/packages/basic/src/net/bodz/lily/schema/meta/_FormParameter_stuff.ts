import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import FormDef from "./FormDef";
import _FormParameter_stuff_Type from "./_FormParameter_stuff_Type";

export class _FormParameter_stuff extends CoEntity<integer> {
    static TYPE = new _FormParameter_stuff_Type();

    id: integer;
    name?: string;
    value?: string;

    form: FormDef;
    formId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _FormParameter_stuff;
