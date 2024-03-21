import CoImagedValidators from './CoImagedValidators';
import CoPrincipalTypeInfo from './CoPrincipalTypeInfo';

export class CoPrincipalValidators extends CoImagedValidators {

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