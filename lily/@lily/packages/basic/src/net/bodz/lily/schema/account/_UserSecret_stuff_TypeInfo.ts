import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import User from "./User";
import _UserSecret_stuff_Validators from "./_UserSecret_stuff_Validators";

export class _UserSecret_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "usersec";

    get name() { return "net.bodz.lily.schema.account.UserSecret"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Secret"; }

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
        id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
        password: property({ type: STRING, nullable: false, precision: 40, 
            description: "Password data", 
            validator: this.validators.validatePassword }),
        question: property({ type: STRING, precision: 100, 
            description: "Protection question", 
            validator: this.validators.validateQuestion }),
        answer: property({ type: STRING, precision: 30, 
            description: "Protection answer", 
            validator: this.validators.validateAnswer }),

        user: property({ type: User.TYPE, nullable: false, 
            description: "The declaring user", 
            validator: this.validators.validateUser }),
        userId: property({ type: INT, nullable: false, precision: 10, 
            description: "The declaring user" }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _UserSecret_stuff_TypeInfo;
