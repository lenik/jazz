import { LONG } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import FavRecordTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/FavRecordTypeInfo";

import Issue from "./Issue";
import _IssueFav_stuff_Validators from "./_IssueFav_stuff_Validators";

export class _IssueFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "issue_fav";

    static readonly FIELD_ISSUE_ID = "issue";

    static readonly N_ISSUE_ID = 19;

    readonly validators = new _IssueFav_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.issue.IssueFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            issue: property({ type: Issue.TYPE, nullable: false, validator: this.validators.validateIssue }),
            issueId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _IssueFav_stuff_TypeInfo();

}

export default _IssueFav_stuff_TypeInfo;
