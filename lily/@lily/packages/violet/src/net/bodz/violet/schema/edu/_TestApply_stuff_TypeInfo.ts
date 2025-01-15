import { BIG_DECIMAL, INT, LONG } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";
import { Person_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import { TestPaper_TYPE } from "./TestPaperTypeInfo";
import _TestApply_stuff_Validators from "./_TestApply_stuff_Validators";

export class _TestApply_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "testapply";

    static readonly FIELD_PAPER_ID = "paper";
    static readonly FIELD_PERSON_ID = "person";
    static readonly FIELD_SCORE = "score";

    static readonly N_PAPER_ID = 10;
    static readonly N_PERSON_ID = 10;
    static readonly N_SCORE = 10;

    readonly validators = new _TestApply_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.edu.TestApply"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            score: property({ type: BIG_DECIMAL, precision: 10, scale: 2, validator: this.validators.validateScore }),

            person: property({ type: Person_TYPE, validator: this.validators.validatePerson }),
            personId: property({ type: INT, precision: 10 }),

            paper: property({ type: TestPaper_TYPE, validator: this.validators.validatePaper }),
            paperId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _TestApply_stuff_TypeInfo();

}

export default _TestApply_stuff_TypeInfo;

export const _TestApply_stuff_TYPE = _TestApply_stuff_TypeInfo.INSTANCE;
