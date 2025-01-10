import { IEntityType } from 'skel01-dba/src/net/bodz/lily/entity/IEntityType';
import IdEntityValidators from './IdEntityValidators';
import FavRecordTypeInfo from './FavRecordTypeInfo';
import User from '../schema/account/User';

export class FavRecordValidators extends IdEntityValidators {

    constructor(type: FavRecordTypeInfo) {
        super(type);
    }

    get type(): FavRecordTypeInfo {
        return this._type as FavRecordTypeInfo;
    }

    validateUser(val: User) { }
    validateVoteCount(val: number) { }

}

export default FavRecordValidators;