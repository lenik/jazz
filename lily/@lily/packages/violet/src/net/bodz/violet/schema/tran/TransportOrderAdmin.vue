<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import SalesOrder from "../shop/SalesOrder";
import StoreOrder from "../store/StoreOrder";
import TransportCategory from "./TransportCategory";
import TransportOrder from "./TransportOrder";
import TransportPhase from "./TransportPhase";

export const title = "Admin view of: Transport order";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import TransportOrderEditor from "./TransportOrderEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = TransportOrder.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "User": User.TYPE,
    "Group": Group.TYPE,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
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

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="INT" data-field="accessMode">Access Mode</th>
            <th data-type="INT" data-field="acl">Acl</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
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
        </template>
        <template #preview>
            <TransportOrderEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <TransportOrderEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
