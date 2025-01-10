<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import { Region_TYPE } from "./RegionTypeInfo";
import StoreOrderItem from "./StoreOrderItem";
import { StoreOrder_TYPE } from "./StoreOrderTypeInfo";

export const title = "Admin view of: Store order item";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

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
    "OffsetDateTime": OffsetDateTime.TYPE,
    "StoreOrder": StoreOrder_TYPE,
    "Artifact": Artifact_TYPE,
    "Region": Region_TYPE,
    "JSON_VARIANT": JSON_VARIANT,
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
            <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="StoreOrder" data-format="label" data-field="order">Order</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
            <th data-type="Region" data-format="label" data-field="region">Region</th>
            <th data-type="JSON_VARIANT" data-field="batch">Batch</th>
            <th data-type="LONG" data-field="serial">Serial</th>
            <th data-type="OffsetDateTime" data-field="expire">Expire</th>
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
