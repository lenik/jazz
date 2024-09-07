import CoEventValidators from '@lily/basic/src/net/bodz/lily/concrete/CoEventValidators';
import AbstractAssetTypeInfo from './AbstractAssetTypeInfo';
import Artifact from '../art/Artifact';

export class AbstractAssetValidators extends CoEventValidators {

    constructor(type: AbstractAssetTypeInfo) {
        super(type);
    }

    validateArtifact(val: Artifact) {
    }

}

export default AbstractAssetValidators;