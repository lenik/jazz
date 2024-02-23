import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { User } from "./User";
import UserSecretValidators from "./UserSecretValidators";

export class _UserSecret_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "usersec";

    name = "net.bodz.lily.schema.account.UserSecret"
    icon = "fa-tag"
    description = "User Secret"

    static FIELD_ID = "id";
    static FIELD_USER_ID = "user";
    static FIELD_PASSWORD = "passwd";
    static FIELD_QUESTION = "question";
    static FIELD_ANSWER = "answer";

    static N_ID = 10;
    static N_USER_ID = 10;
    static N_PASSWORD = 40;
    static N_QUESTION = 100;
    static N_ANSWER = 30;

    static validators = new UserSecretValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        password: property({ type: "string", nullable: false, precision: 40, 
            description: "Password data", 
            validator: this.validators.validatePassword }),
        question: property({ type: "string", precision: 100, 
            description: "Protection question", 
            validator: this.validators.validateQuestion }),
        answer: property({ type: "string", precision: 30, 
            description: "Protection answer", 
            validator: this.validators.validateAnswer }),

        user: property({ type: User.TYPE, nullable: false, 
            description: "The declaring user", 
            validator: this.validators.validateUser }),
        userId: property({ type: "integer", nullable: false, precision: 10, 
            description: "The declaring user" }),
    }

    constructor() {
        super();
        this.declare(_UserSecret_stuff_Type.declaredProperty);
    }

}

export default _UserSecret_stuff_Type;
