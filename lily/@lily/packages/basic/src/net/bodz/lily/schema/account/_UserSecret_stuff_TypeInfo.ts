import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { User_TYPE } from "./UserTypeInfo";
import _UserSecret_stuff_Validators from "./_UserSecret_stuff_Validators";

export class _UserSecret_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "usersec";

    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_USER_ID = "user";
    static readonly FIELD_PASSWORD = "passwd";
    static readonly FIELD_QUESTION = "question";
    static readonly FIELD_ANSWER = "answer";

    static readonly N_PROPERTIES = 2147483647;
    static readonly N_USER_ID = 10;
    static readonly N_PASSWORD = 40;
    static readonly N_QUESTION = 100;
    static readonly N_ANSWER = 30;

    readonly validators = new _UserSecret_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.account.UserSecret"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Secret"; }

    override preamble() {
        super.preamble();
        this.declare({
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

            user: property({ type: User_TYPE, nullable: false, 
                description: "The declaring user", 
                validator: this.validators.validateUser }),
            userId: property({ type: INT, nullable: false, precision: 10, 
                description: "The declaring user" }),
        });
    }

    static readonly INSTANCE = new _UserSecret_stuff_TypeInfo();

}

export default _UserSecret_stuff_TypeInfo;

export const _UserSecret_stuff_TYPE = _UserSecret_stuff_TypeInfo.INSTANCE;
