import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import Diary from "./Diary";
import _DiaryParty_stuff_Validators from "./_DiaryParty_stuff_Validators";

export class _DiaryParty_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "diary_party";

    static readonly FIELD_ID = "id";
    static readonly FIELD_DIARY_ID = "diary";
    static readonly FIELD_USER_ID = "user";
    static readonly FIELD_PERSON_ID = "person";
    static readonly FIELD_ORG_ID = "org";
    static readonly FIELD_VALUE = "value";

    static readonly N_ID = 19;
    static readonly N_DIARY_ID = 19;
    static readonly N_USER_ID = 10;
    static readonly N_PERSON_ID = 10;
    static readonly N_ORG_ID = 10;
    static readonly N_VALUE = 10;

    readonly validators = new _DiaryParty_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryParty"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: LONG, nullable: false, precision: 19, validator: this.validators.validateId }),
            value: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateValue }),

            person: property({ type: Person.TYPE, validator: this.validators.validatePerson }),
            personId: property({ type: INT, precision: 10 }),

            org: property({ type: Organization.TYPE, validator: this.validators.validateOrg }),
            orgId: property({ type: INT, precision: 10 }),

            user: property({ type: User.TYPE, inheritsDoc: true, 
                description: "User Account", 
                validator: this.validators.validateUser }),
            userId: property({ type: INT, precision: 10 }),

            diary: property({ type: Diary.TYPE, nullable: false, validator: this.validators.validateDiary }),
            diaryId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _DiaryParty_stuff_TypeInfo();

}

export default _DiaryParty_stuff_TypeInfo;
