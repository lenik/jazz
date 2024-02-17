
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _UserSecret_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "usersec";

    name = "net.bodz.lily.schema.account.UserSecret"
    icon = "fa-tag"
    description = "User Secret"

    static const FIELD_ID = "id";
    static const FIELD_USER_ID = "user";
    static const FIELD_PASSWORD = "passwd";
    static const FIELD_QUESTION = "question";
    static const FIELD_ANSWER = "answer";

    static const N_ID = 10;
    static const N_USER_ID = 10;
    static const N_PASSWORD = 40;
    static const N_QUESTION = 100;
    static const N_ANSWER = 30;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        password: property({ type: "string", nullable: false, precision: 40, 
            description: "Password data", 
            validator: validators.validate_password }),
        question: property({ type: "string", precision: 100, 
            description: "Protection question", 
            validator: validators.validate_question }),
        answer: property({ type: "string", precision: 30, 
            description: "Protection answer", 
            validator: validators.validate_answer }),

        user: property({ type: "net.bodz.lily.schema.account.User", nullable: false, 
            description: "The declaring user", 
            validator: validators.validate_user }),
        userId: property({ type: "int", nullable: false, precision: 10, 
            description: "The declaring user", 
            validator: validators.validate_userId }),
    }

    constructor() {
        super();
        this.declare(_UserSecret_stuff_Type.declaredProperty);
    }

}
