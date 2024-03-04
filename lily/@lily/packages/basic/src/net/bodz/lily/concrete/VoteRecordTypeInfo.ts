import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import VoteRecordValidators from './VoteRecordValidators';
import User from '../schema/account/User';

export class VoteRecordTypeInfo extends IdEntityTypeInfo {

    get name() { return "net.bodz.lily.concrete.VoteRecordType"; }
    get icon() { return "far-arrow-alt-circle-up"; }
    get label() { return "Vote Record"; }
    get description() { return "Vote up/down events."; }

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