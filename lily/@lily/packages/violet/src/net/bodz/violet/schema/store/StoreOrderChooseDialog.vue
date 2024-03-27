<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";
import Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import OrgUnit from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnit";
import Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";
import FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import Plan from "../plan/Plan";
import StoreCategory from "./StoreCategory";
import StoreOrder from "./StoreOrder";
import { StoreOrder } from "./StoreOrder";
import StorePhase from "./StorePhase";

export const title = "Choose dialog for: Store order";
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
    "OffsetDateTime": OffsetDateTime.TYPE,
    "STRING": STRING,
    "FormDef": FormDef.TYPE,
    "StoreCategory": StoreCategory.TYPE,
    "StorePhase": StorePhase.TYPE,
    "StoreOrder": StoreOrder.TYPE,
    "Plan": Plan.TYPE,
    "Organization": Organization.TYPE,
    "OrgUnit": OrgUnit.TYPE,
    "Person": Person.TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="StoreOrder.TYPE" :typeMap="typeMap" :modal="modal">
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
        <th data-type="StoreCategory" data-format="label" data-field="category">Category</th>
        <th data-type="StorePhase" data-format="label" data-field="phase">Phase</th>
        <th data-type="StoreOrder" data-format="label" data-field="prev">Prev</th>
        <th data-type="Plan" data-format="label" data-field="plan">Plan</th>
        <th data-type="Organization" data-format="label" data-field="org">Org</th>
        <th data-type="OrgUnit" data-format="label" data-field="orgUnit">Org Unit</th>
        <th data-type="Person" data-format="label" data-field="person">Person</th>
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
