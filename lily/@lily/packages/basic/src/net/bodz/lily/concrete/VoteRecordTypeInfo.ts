import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityTypeInfo from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntityTypeInfo';
import VoteRecordValidators from './VoteRecordValidators';

export class VoteRecordTypeInfo extends IdEntityTypeInfo {

    name = "net.bodz.lily.concrete.VoteRecordType"
    icon = "far-arrow-alt-circle-up"
    label = "Vote Record"
    description = "Vote up/down events."

    validators = new VoteRecordValidators();

    declaredProperty: EntityPropertyMap = {
        user: property({ type: "any", icon: "far-user" }),
        voteCount: property({ type: "number", precision: 10, icon: "far-hashtag" }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default VoteRecordTypeInfo;