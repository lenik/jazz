<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import { SalesOrderItem } from "./SalesOrderItem";
import { SalesOrder_TYPE } from "./SalesOrderTypeInfo";
import { ShopItem_TYPE } from "./ShopItemTypeInfo";

export const title = "Choose dialog for: Sales order item";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "@skeljs/dba/src/ui/lily/EntityChooseDialog.vue";

const model = defineModel();

const props = withDefaults(defineProps<Props>(), {
    modal: true
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

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

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

onMounted(() => {
});

</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="SalesOrderItem.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="INT" data-field="priority">Priority</th>
        <th data-type="INT" data-field="flags">Flags</th>
        <th data-type="DefaultState" data-field="state">State</th>
        <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
        <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
        <th data-type="INT" data-field="year">Year</th>
        <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
        <th data-type="SalesOrder" data-format="label" data-field="order">Order</th>
        <th data-type="ShopItem" data-format="label" data-field="shopItem">Shop Item</th>
        <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
        <th data-type="JSON_VARIANT" data-field="batch">Batch</th>
        <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
        <th data-type="BIG_DECIMAL" data-field="price">Price</th>
        <th data-type="BIG_DECIMAL" data-field="amount">Amount</th>
        <th data-type="BIG_DECIMAL" data-field="n1">N1</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
