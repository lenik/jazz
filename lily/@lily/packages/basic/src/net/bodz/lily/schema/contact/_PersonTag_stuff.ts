import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import Person from "./Person";
import PersonTagType from "./PersonTagType";
import _PersonTag_stuff_TypeInfo from "./_PersonTag_stuff_TypeInfo";

export class _PersonTag_stuff extends CoEntity<integer> {
    static TYPE = new _PersonTag_stuff_TypeInfo();

    id: integer;

    tag: PersonTagType;
    tagId: integer;

    person: Person;
    personId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _PersonTag_stuff;
