import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import DocRecordValidators from './DocRecordValidators';

export class DocRecordTypeInfo extends CoMessageTypeInfo {

    get name() { return "net.bodz.lily.concrete.DocRecord"; }
    get icon() { return "fa-envelop"; }
    get label() { return "Doc Record"; }
    get description() { return "Doc records."; }

    validators = new DocRecordValidators(this);

    declaredProperty: EntityPropertyMap = {
        read: property({ type: 'boolean', icon: "far-eye", }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default DocRecordTypeInfo;