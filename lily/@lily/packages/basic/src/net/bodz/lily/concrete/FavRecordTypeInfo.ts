import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import FavRecordValidators from './FavRecordValidators';
import { INT, LONG } from '@skeljs/core/src/lang/baseinfo';
import User from '../schema/account/User';

export class FavRecordTypeInfo extends IdEntityTypeInfo {

    get name() { return "net.bodz.lily.concrete.FavRecordType"; }
    get icon() { return "fab-gratipay"; }
    get label() { return "Favorite Record"; }
    get description() { return "User favorite record."; }

    validators = new FavRecordValidators(this);

    declaredProperty: EntityPropertyMap = {
        user: property({ type: User.TYPE, icon: "far-user" }),
        voteCount: property({ type: INT, precision: 10, icon: "far-hashtag" }),
    };

    constructor() {
        super(LONG);
        this.declare(this.declaredProperty);
    }

}

export default FavRecordTypeInfo;