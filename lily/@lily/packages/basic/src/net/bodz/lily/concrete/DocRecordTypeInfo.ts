import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import DocRecordValidators from './DocRecordValidators';

export class DocRecordTypeInfo extends CoMessageTypeInfo {

    name = "net.bodz.lily.concrete.DocRecord"
    icon = "fa-envelop"
    label = "Doc Record"
    description = "Doc records."

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