import { IEntityType } from 'skel01-dba/src/net/bodz/lily/entity/IEntityType';
import CoMessageValidators from './CoMessageValidators';
import CoTalkTypeInfo from './CoTalkTypeInfo';
import CoTalk from './CoTalk';

export class CoTalkValidators extends CoMessageValidators {

    constructor(type: CoTalkTypeInfo) {
        super(type);
    }

    get type(): CoTalkTypeInfo {
        return this._type as CoTalkTypeInfo;
    }

    validateParent(val: CoTalk<any>) { }

}

export default CoTalkValidators;
