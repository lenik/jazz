import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoCategoryTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoCategoryTypeInfo";

import _ArtifactCategory_stuff_Validators from "./_ArtifactCategory_stuff_Validators";

export class _ArtifactCategory_stuff_TypeInfo extends CoCategoryTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "artcat";

    static readonly FIELD_CODE = "code";
    static readonly FIELD_SKUFMT = "skufmt";
    static readonly FIELD_SKUFMTFULL = "skufmtfull";
    static readonly FIELD_BARFMT = "barfmt";
    static readonly FIELD_BARFMTFULL = "barfmtfull";
    static readonly FIELD_BATCHFMT = "batchfmt";
    static readonly FIELD_SERIALFMT = "serialfmt";
    static readonly FIELD_COUNT = "count";

    static readonly N_CODE = 20;
    static readonly N_SKUFMT = 100;
    static readonly N_SKUFMTFULL = 100;
    static readonly N_BARFMT = 100;
    static readonly N_BARFMTFULL = 100;
    static readonly N_BATCHFMT = 100;
    static readonly N_SERIALFMT = 100;
    static readonly N_COUNT = 10;

    readonly validators = new _ArtifactCategory_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({ type: STRING, precision: 20, validator: this.validators.validateCode }),
            skufmt: property({ type: STRING, precision: 100, validator: this.validators.validateSkufmt }),
            skufmtfull: property({ type: STRING, precision: 100, validator: this.validators.validateSkufmtfull }),
            barfmt: property({ type: STRING, precision: 100, validator: this.validators.validateBarfmt }),
            barfmtfull: property({ type: STRING, precision: 100, validator: this.validators.validateBarfmtfull }),
            batchfmt: property({ type: STRING, precision: 100, validator: this.validators.validateBatchfmt }),
            serialfmt: property({ type: STRING, precision: 100, validator: this.validators.validateSerialfmt }),
            count: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateCount }),
        });
    }

}

export default _ArtifactCategory_stuff_TypeInfo;

export const _ArtifactCategory_stuff_TYPE = _ArtifactCategory_stuff_TypeInfo.INSTANCE;
