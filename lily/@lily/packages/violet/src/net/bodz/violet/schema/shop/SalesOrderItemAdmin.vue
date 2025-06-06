<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import SalesOrderItem from "./SalesOrderItem";
import { SalesOrder_TYPE } from "./SalesOrderTypeInfo";
import { ShopItem_TYPE } from "./ShopItemTypeInfo";

export const title = "Admin view of: Sales order item";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import SalesOrderItemEditor from "./SalesOrderItemEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = SalesOrderItem.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "SalesOrder": SalesOrder_TYPE,
    "ShopItem": ShopItem_TYPE,
    "Artifact": Artifact_TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
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
            <th class="hidden" data-type="JSON_VARIANT" data-field="properties">Properties</th>
            <th data-type="JSON_VARIANT" data-field="files">Files</th>
            <th data-type="SalesOrder" data-format="label" data-field="order">Order</th>
            <th data-type="ShopItem" data-format="label" data-field="shopItem">Shop Item</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
            <th data-type="JSON_VARIANT" data-field="batch">Batch</th>
            <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
            <th data-type="BIG_DECIMAL" data-field="price">Price</th>
            <th data-type="BIG_DECIMAL" data-field="amount">Amount</th>
            <th data-type="BIG_DECIMAL" data-field="n1">N1</th>
        </template>
        <template #preview>
            <SalesOrderItemEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <SalesOrderItemEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
