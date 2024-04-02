<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";

import { Artifact_TYPE } from "../art/ArtifactTypeInfo";
import TransportOrderItem from "./TransportOrderItem";
import { TransportOrder_TYPE } from "./TransportOrderTypeInfo";

export const title = "Admin view of: Transport order item";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import TransportOrderItemEditor from "./TransportOrderItemEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = TransportOrderItem.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "TransportOrder": TransportOrder_TYPE,
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
            <th data-type="TransportOrder" data-format="label" data-field="order">Order</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
            <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
            <th data-type="BIG_DECIMAL" data-field="price">Price</th>
            <th data-type="BIG_DECIMAL" data-field="amount">Amount</th>
        </template>
        <template #preview>
            <TransportOrderItemEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <TransportOrderItemEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
