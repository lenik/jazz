import { BigDecimal, int, long } from 'skel01-core/src/lang/basetype';
import CoEvent from '@lily/basic/src/net/bodz/lily/concrete/CoEvent';
import AbstractAssetTypeInfo from './AbstractAssetTypeInfo';
import Artifact from '../art/Artifact';
import Region from '../store/Region';
import { ZonedDateTime } from 'skel01-core/src/lang/time';

export abstract class AbstractAsset extends CoEvent<long> {

    static readonly TYPE = AbstractAssetTypeInfo.INSTANCE;

    artifact: Artifact
    region?: Region

    // Batch complex
    quantity?: BigDecimal
    serial?: long
    expire?: ZonedDateTime

    constructor(o: any) {
        super(o);
    }

}

export default AbstractAsset;