<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import { TransportOrderItem } from "./TransportOrderItem";
import { TransportOrder_TYPE } from "./TransportOrderTypeInfo";

export const title = "Choose dialog for: Transport order item";
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
    "TransportOrder": TransportOrder_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="TransportOrderItem.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="INT" data-field="priority">Priority</th>
        <th data-type="INT" data-field="flags">Flags</th>
        <th data-type="DefaultState" data-field="state">State</th>
        <th data-type="TransportOrder" data-format="label" data-field="order">Order</th>
        <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
        <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
        <th data-type="BIG_DECIMAL" data-field="price">Price</th>
        <th data-type="BIG_DECIMAL" data-field="amount">Amount</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
