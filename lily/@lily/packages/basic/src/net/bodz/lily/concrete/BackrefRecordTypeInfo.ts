import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import BackrefRecordValidators from './BackrefRecordValidators';
import User from '../schema/account/User';
import { LONG, STRING } from '@skeljs/core/src/lang/baseinfo';

export class BackrefRecordTypeInfo extends IdEntityTypeInfo {

    get name() { return "net.bodz.lily.concrete.BackrefRecordType"; }
    get icon() { return "fas-retweet"; }
    get label() { return "Backref Record"; }
    get description() { return "Shared link to external sites."; }

    validators = new BackrefRecordValidators(this);

    declaredProperty: EntityPropertyMap = {
        user: property({
            type: User.TYPE, icon: "far-user",
            validator: this.validators.validateUser
        }),
        site: property({
            type: STRING, precision: 100, icon: "far-link",
            validator: this.validators.validateSite
        }),
    };

    constructor() {
        super(LONG);
        this.declare(this.declaredProperty);
    }

}

export default BackrefRecordTypeInfo;