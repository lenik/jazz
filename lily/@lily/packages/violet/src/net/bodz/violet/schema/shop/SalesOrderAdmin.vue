<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";
import FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import Plan from "../plan/Plan";
import SalesCategory from "./SalesCategory";
import SalesOrder from "./SalesOrder";
import SalesPhase from "./SalesPhase";

export const title = "Admin view of: Sales order";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import SalesOrderEditor from "./SalesOrderEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = SalesOrder.TYPE;
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
    "SalesCategory": SalesCategory.TYPE,
    "SalesPhase": SalesPhase.TYPE,
    "SalesOrder": SalesOrder.TYPE,
    "Plan": Plan.TYPE,
    "Organization": Organization.TYPE,
    "Person": Person.TYPE,
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
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="STRING" data-field="subject">Subject</th>
            <th data-type="User" data-format="label" data-field="op">Op</th>
            <th data-type="STRING" data-field="rawText">Raw Text</th>
            <th data-type="FormDef" data-format="label" data-field="form">Form</th>
            <th data-type="STRING" data-field="formArguments">Form Arguments</th>
            <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
            <th data-type="SalesCategory" data-format="label" data-field="category">Category</th>
            <th data-type="SalesPhase" data-format="label" data-field="phase">Phase</th>
            <th data-type="SalesOrder" data-format="label" data-field="previousOrder">Previous Order</th>
            <th data-type="Plan" data-format="label" data-field="plan">Plan</th>
            <th data-type="Organization" data-format="label" data-field="customerOrg">Customer Org</th>
            <th data-type="Person" data-format="label" data-field="customer">Customer</th>
            <th data-type="INT" data-field="length">Length</th>
            <th data-type="BIG_DECIMAL" data-field="totalQuantity">Total Quantity</th>
            <th data-type="BIG_DECIMAL" data-field="totalAmount">Total Amount</th>
        </template>
        <template #preview>
            <SalesOrderEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <SalesOrderEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
