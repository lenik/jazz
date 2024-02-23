import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import Person from "./Person";
import PersonTagType from "./PersonTagType";
import _PersonTag_stuff_Type from "./_PersonTag_stuff_Type";

export class _PersonTag_stuff extends CoEntity<integer> {
    static TYPE = new _PersonTag_stuff_Type();

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
