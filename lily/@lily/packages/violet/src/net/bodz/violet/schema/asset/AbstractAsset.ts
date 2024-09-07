import { BigDecimal, int, long } from '@skeljs/core/src/lang/basetype';
import CoEvent from '@lily/basic/src/net/bodz/lily/concrete/CoEvent';
import Artifact from '../art/Artifact';
import Region from '../store/Region';
import { ZonedDateTime } from '@skeljs/core/src/lang/time';

export abstract class AbstractAsset extends CoEvent<long> {

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