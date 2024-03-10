import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/IEntityType';
import IdEntityValidators from './IdEntityValidators';
import CoPrincipalTypeInfo from './CoPrincipalTypeInfo';

export class CoPrincipalValidators extends IdEntityValidators {

    constructor(type: CoPrincipalTypeInfo) {
        super(type);
    }

    get type(): CoPrincipalTypeInfo {
        return this._type as CoPrincipalTypeInfo;
    }

    validateName(val: string) {

    }

    validateProperties(val: any) {

    }

}

export default CoPrincipalValidators;