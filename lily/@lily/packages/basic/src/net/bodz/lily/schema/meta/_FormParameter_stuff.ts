import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type FormDef from "./FormDef";
import _FormParameter_stuff_TypeInfo from "./_FormParameter_stuff_TypeInfo";

export class _FormParameter_stuff extends CoEntity<integer> {
    static TYPE = new _FormParameter_stuff_TypeInfo();

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
