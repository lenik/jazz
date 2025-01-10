import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import FavRecordValidators from './FavRecordValidators';
import { INT, LONG } from 'skel01-core/src/lang/baseinfo';
import User from '../schema/account/User';

export class FavRecordTypeInfo extends IdEntityTypeInfo {

    readonly validators = new FavRecordValidators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.lily.concrete.FavRecordType"; }
    get icon() { return "fab-gratipay"; }
    get label() { return "Favorite Record"; }
    get description() { return "User favorite record."; }


    override preamble() {
        super.preamble();
        this.declare({
            user: property({ type: User.TYPE, icon: "far-user" }),
            voteCount: property({ type: INT, precision: 10, icon: "far-hashtag" }),
        });
    }

    static readonly INSTANCE = new FavRecordTypeInfo();

}

export default FavRecordTypeInfo;
export const FavRecord_TYPE = FavRecordTypeInfo.INSTANCE;
