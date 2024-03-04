import { IEntityType } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoObjectValidators from './CoObjectValidators';
import CoPrincipalTypeInfo from './CoPrincipalTypeInfo';

export class CoPrincipalValidators extends CoObjectValidators {

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