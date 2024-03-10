<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import { TIMESTAMP } from "@skeljs/core/src/lang/time";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";

import Artifact from "../art/Artifact";
import Region from "./Region";
import StoreOrder from "./StoreOrder";
import StoreOrderItem from "./StoreOrderItem";

export const title = "Admin view of: Store order item";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import StoreOrderItemEditor from "./StoreOrderItemEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = StoreOrderItem.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "StoreOrder": StoreOrder.TYPE,
    "Artifact": Artifact.TYPE,
    "Region": Region.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "TIMESTAMP": TIMESTAMP,
    "BIG_DECIMAL": BIG_DECIMAL,
    "STRING": STRING,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="ZonedDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="ZonedDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="StoreOrder" data-format="label" data-field="order">Order</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
            <th data-type="Region" data-format="label" data-field="region">Region</th>
            <th data-type="JSON_VARIANT" data-field="batch">Batch</th>
            <th data-type="LONG" data-field="serial">Serial</th>
            <th data-type="TIMESTAMP" data-field="expire">Expire</th>
            <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
            <th data-type="BIG_DECIMAL" data-field="price">Price</th>
            <th data-type="BIG_DECIMAL" data-field="amount">Amount</th>
            <th data-type="STRING" data-field="notes">Notes</th>
        </template>
        <template #preview>
            <StoreOrderItemEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <StoreOrderItemEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
