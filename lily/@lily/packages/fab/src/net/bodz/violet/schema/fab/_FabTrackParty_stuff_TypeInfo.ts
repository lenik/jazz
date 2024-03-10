import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import FabTrack from "./FabTrack";
import _FabTrackParty_stuff_Validators from "./_FabTrackParty_stuff_Validators";

export class _FabTrackParty_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabtrack_op";

    static readonly FIELD_ID = "id";
    static readonly FIELD_TRACK_ID = "track";
    static readonly FIELD_PERSON_ID = "person";
    static readonly FIELD_ROLE = "role";

    static readonly N_ID = 19;
    static readonly N_TRACK_ID = 19;
    static readonly N_PERSON_ID = 10;
    static readonly N_ROLE = 30;

    readonly validators = new _FabTrackParty_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabTrackParty"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: LONG, nullable: false, precision: 19, validator: this.validators.validateId }),
            role: property({ type: STRING, precision: 30, validator: this.validators.validateRole }),

            person: property({ type: Person.TYPE, nullable: false, validator: this.validators.validatePerson }),
            personId: property({ type: INT, nullable: false, precision: 10 }),

            track: property({ type: FabTrack.TYPE, nullable: false, validator: this.validators.validateTrack }),
            trackId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _FabTrackParty_stuff_TypeInfo();

}

export default _FabTrackParty_stuff_TypeInfo;
