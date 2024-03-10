import type { List } from "@skeljs/core/src/lang/basetype";

import PersonTypeInfo from "./PersonTypeInfo";
import _Person_stuff from "./_Person_stuff";

export class Person extends _Person_stuff {

    static _typeInfo: PersonTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PersonTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    hello?: string
    peers?: List<string>

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Person;
