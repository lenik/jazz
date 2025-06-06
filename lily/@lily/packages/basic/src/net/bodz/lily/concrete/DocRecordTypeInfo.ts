import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import DocRecordValidators from './DocRecordValidators';
import { BOOLEAN, LONG } from 'skel01-core/src/lang/baseinfo';

export class DocRecordTypeInfo extends CoMessageTypeInfo {

    readonly validators = new DocRecordValidators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.lily.concrete.DocRecord"; }
    get icon() { return "fa-envelop"; }
    get label() { return "Doc Record"; }
    get description() { return "Doc records."; }

    override preamble() {
        super.preamble();
        this.declare({
            read: property({ type: BOOLEAN, icon: "far-eye", }),
        });
    }

    static readonly INSTANCE = new DocRecordTypeInfo();

}

export default DocRecordTypeInfo;
export const DocRecord_TYPE = DocRecordTypeInfo.INSTANCE;
