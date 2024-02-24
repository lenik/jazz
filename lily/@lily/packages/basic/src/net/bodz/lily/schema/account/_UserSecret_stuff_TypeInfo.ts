import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import UserTypeInfo from "./UserTypeInfo";
import _UserSecret_stuff_Validators from "./_UserSecret_stuff_Validators";

export class _UserSecret_stuff_TypeInfo extends CoEntityTypeInfo {

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

    validators = new _UserSecret_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
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

        user: property({ type: UserTypeInfo, nullable: false, 
            description: "The declaring user", 
            validator: this.validators.validateUser }),
        userId: property({ type: "integer", nullable: false, precision: 10, 
            description: "The declaring user" }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _UserSecret_stuff_TypeInfo;
