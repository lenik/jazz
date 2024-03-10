import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import User from "./User";
import _UserSecret_stuff_Validators from "./_UserSecret_stuff_Validators";

export class _UserSecret_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "usersec";

    static readonly FIELD_ID = "id";
    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_USER_ID = "user";
    static readonly FIELD_PASSWORD = "passwd";
    static readonly FIELD_QUESTION = "question";
    static readonly FIELD_ANSWER = "answer";

    static readonly N_ID = 10;
    static readonly N_PROPERTIES = 2147483647;
    static readonly N_USER_ID = 10;
    static readonly N_PASSWORD = 40;
    static readonly N_QUESTION = 100;
    static readonly N_ANSWER = 30;

    readonly validators = new _UserSecret_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.UserSecret"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Secret"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
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
        });
    }

    static readonly INSTANCE = new _UserSecret_stuff_TypeInfo();

}

export default _UserSecret_stuff_TypeInfo;
