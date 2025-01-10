import { INT, LONG } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { Organization_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";
import { Person_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import { Diary_TYPE } from "./DiaryTypeInfo";
import _DiaryParty_stuff_Validators from "./_DiaryParty_stuff_Validators";

export class _DiaryParty_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "diary_party";

    static readonly FIELD_DIARY_ID = "diary";
    static readonly FIELD_USER_ID = "user";
    static readonly FIELD_PERSON_ID = "person";
    static readonly FIELD_ORG_ID = "org";
    static readonly FIELD_VALUE = "value";

    static readonly N_DIARY_ID = 19;
    static readonly N_USER_ID = 10;
    static readonly N_PERSON_ID = 10;
    static readonly N_ORG_ID = 10;
    static readonly N_VALUE = 10;

    readonly validators = new _DiaryParty_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryParty"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            value: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateValue }),

            person: property({ type: Person_TYPE, validator: this.validators.validatePerson }),
            personId: property({ type: INT, precision: 10 }),

            org: property({ type: Organization_TYPE, validator: this.validators.validateOrg }),
            orgId: property({ type: INT, precision: 10 }),

            user: property({ type: User_TYPE, inheritsDoc: true, 
                description: "User Account", 
                validator: this.validators.validateUser }),
            userId: property({ type: INT, precision: 10 }),

            diary: property({ type: Diary_TYPE, nullable: false, validator: this.validators.validateDiary }),
            diaryId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _DiaryParty_stuff_TypeInfo();

}

export default _DiaryParty_stuff_TypeInfo;

export const _DiaryParty_stuff_TYPE = _DiaryParty_stuff_TypeInfo.INSTANCE;
