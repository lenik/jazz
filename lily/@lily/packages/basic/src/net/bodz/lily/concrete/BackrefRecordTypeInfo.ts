import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import BackrefRecordValidators from './BackrefRecordValidators';
import User from '../schema/account/User';

export class BackrefRecordTypeInfo extends IdEntityTypeInfo {

    name = "net.bodz.lily.concrete.BackrefRecordType"
    icon = "fas-retweet"
    label = "Backref Record"
    description = "Shared link to external sites."

    validators = new BackrefRecordValidators(this);

    declaredProperty: EntityPropertyMap = {
        user: property({
            type: User.TYPE, icon: "far-user",
            validator: this.validators.validateUser
        }),
        site: property({
            type: "string", precision: 100, icon: "far-link",
            validator: this.validators.validateSite
        }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default BackrefRecordTypeInfo;