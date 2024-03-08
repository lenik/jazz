import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import DocRecordValidators from './DocRecordValidators';
import { BOOLEAN, LONG } from '@skeljs/core/src/lang/baseinfo';

export class DocRecordTypeInfo extends CoMessageTypeInfo {

    get name() { return "net.bodz.lily.concrete.DocRecord"; }
    get icon() { return "fa-envelop"; }
    get label() { return "Doc Record"; }
    get description() { return "Doc records."; }

    validators = new DocRecordValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
            read: property({ type: BOOLEAN, icon: "far-eye", }),
        });
    }

    constructor() {
        super(LONG);
    }

}

export default DocRecordTypeInfo;