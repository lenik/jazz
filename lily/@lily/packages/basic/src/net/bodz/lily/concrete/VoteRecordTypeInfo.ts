import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import VoteRecordValidators from './VoteRecordValidators';
import User from '../schema/account/User';

export class VoteRecordTypeInfo extends IdEntityTypeInfo {

    name = "net.bodz.lily.concrete.VoteRecordType"
    icon = "far-arrow-alt-circle-up"
    label = "Vote Record"
    description = "Vote up/down events."

    validators = new VoteRecordValidators(this);

    declaredProperty: EntityPropertyMap = {
        user: property({
            type: User.TYPE, icon: "far-user",
            validator: this.validators.validateUser
        }),
        voteCount: property({
            type: "number", precision: 10, icon: "far-hashtag",
            validator: this.validators.validateVoteCount
        }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default VoteRecordTypeInfo;