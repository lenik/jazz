<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";
import Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import SalesOrder from "../shop/SalesOrder";
import StoreOrder from "../store/StoreOrder";
import TransportCategory from "./TransportCategory";
import TransportOrder from "./TransportOrder";
import { TransportOrder } from "./TransportOrder";
import TransportPhase from "./TransportPhase";

export const title = "Choose dialog for: Transport order";
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
    "User": User.TYPE,
    "Group": Group.TYPE,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "STRING": STRING,
    "FormDef": FormDef.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "TransportCategory": TransportCategory.TYPE,
    "TransportPhase": TransportPhase.TYPE,
    "TransportOrder": TransportOrder.TYPE,
    "SalesOrder": SalesOrder.TYPE,
    "StoreOrder": StoreOrder.TYPE,
    "Organization": Organization.TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="TransportOrder.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
        <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
        <th data-type="INT" data-field="accessMode">Access Mode</th>
        <th data-type="INT" data-field="acl">Acl</th>
        <th data-type="INT" data-field="priority">Priority</th>
        <th data-type="INT" data-field="flags">Flags</th>
        <th data-type="DefaultState" data-field="state">State</th>
        <th data-type="ZonedDateTime" data-field="beginTime">Begin Time</th>
        <th data-type="ZonedDateTime" data-field="endTime">End Time</th>
        <th data-type="INT" data-field="year">Year</th>
        <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="ZonedDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="STRING" data-field="subject">Subject</th>
        <th data-type="User" data-format="label" data-field="op">Op</th>
        <th data-type="STRING" data-field="rawText">Raw Text</th>
        <th data-type="FormDef" data-format="label" data-field="form">Form</th>
        <th data-type="STRING" data-field="formArguments">Form Arguments</th>
        <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
        <th data-type="TransportCategory" data-format="label" data-field="category">Category</th>
        <th data-type="TransportPhase" data-format="label" data-field="phase">Phase</th>
        <th data-type="TransportOrder" data-format="label" data-field="prev">Prev</th>
        <th data-type="SalesOrder" data-format="label" data-field="salesOrder">Sales Order</th>
        <th data-type="StoreOrder" data-format="label" data-field="storeodr">Storeodr</th>
        <th data-type="Organization" data-format="label" data-field="shipper">Shipper</th>
        <th data-type="BIG_DECIMAL" data-field="shipcost">Shipcost</th>
        <th data-type="INT" data-field="length">Length</th>
        <th data-type="BIG_DECIMAL" data-field="totalQuantity">Total Quantity</th>
        <th data-type="BIG_DECIMAL" data-field="totalAmount">Total Amount</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
