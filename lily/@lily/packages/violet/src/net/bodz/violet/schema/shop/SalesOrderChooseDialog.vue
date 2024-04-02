<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";
import { Group_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { Organization_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";
import { Person_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";
import { FormDef_TYPE } from "@lily/basic/src/net/bodz/lily/schema/meta/FormDefTypeInfo";

import { Plan_TYPE } from "../plan/PlanTypeInfo";
import { SalesCategory_TYPE } from "./SalesCategoryTypeInfo";
import { SalesOrder } from "./SalesOrder";
import { SalesOrder_TYPE } from "./SalesOrderTypeInfo";
import { SalesPhase_TYPE } from "./SalesPhaseTypeInfo";

export const title = "Choose dialog for: Sales order";
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
    "User": User_TYPE,
    "Group": Group_TYPE,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "STRING": STRING,
    "FormDef": FormDef_TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "SalesCategory": SalesCategory_TYPE,
    "SalesPhase": SalesPhase_TYPE,
    "SalesOrder": SalesOrder_TYPE,
    "Plan": Plan_TYPE,
    "Organization": Organization_TYPE,
    "Person": Person_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="SalesOrder.TYPE" :typeMap="typeMap" :modal="modal">
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
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
