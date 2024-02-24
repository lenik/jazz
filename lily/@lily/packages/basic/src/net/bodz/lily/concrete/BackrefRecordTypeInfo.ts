import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityTypeInfo from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntityTypeInfo';
import BackrefRecordValidators from './BackrefRecordValidators';

export class BackrefRecordTypeInfo extends IdEntityTypeInfo {

    name = "net.bodz.lily.concrete.BackrefRecordType"
    icon = "fas-retweet"
    label = "Backref Record"
    description = "Shared link to external sites."

    validators = new BackrefRecordValidators();

    declaredProperty: EntityPropertyMap = {
        user: property({ type: "any", icon: "far-user" }),
        site: property({ type: "string", precision: 100, icon: "far-link" }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default BackrefRecordTypeInfo;