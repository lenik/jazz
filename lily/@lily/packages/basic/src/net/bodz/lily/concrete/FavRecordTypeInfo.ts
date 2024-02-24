import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import IdEntityTypeInfo from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntityTypeInfo';
import FavRecordValidators from './FavRecordValidators';

export class FavRecordTypeInfo extends IdEntityTypeInfo {

    name = "net.bodz.lily.concrete.FavRecordType"
    icon = "fab-gratipay"
    label = "Favorite Record"
    description = "User favorite record."

    validators = new FavRecordValidators();

    declaredProperty: EntityPropertyMap = {
        user: property({ type: "any", icon: "far-user" }),
        voteCount: property({ type: "number", precision: 10, icon: "far-hashtag" }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default FavRecordTypeInfo;