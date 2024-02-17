
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { Person } from "./Person";
import type { PersonTagType } from "./PersonTagType";
import type { _PersonTag_stuff_Type } from "./_PersonTag_stuff_Type";

export class _PersonTag_stuff extends CoEntity<Integer> {
    static TYPE = new _PersonTag_stuff_Type();

    id: int;

    tag: PersonTagType;
    tagId: int;

    person: Person;
    personId: int;

    constructor(o: any) {
        super(o);
    }
}
