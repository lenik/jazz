<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import { Group_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { OrgUnit_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnitTypeInfo";
import { Organization_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";
import { Person_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";
import { FormDef_TYPE } from "@lily/basic/src/net/bodz/lily/schema/meta/FormDefTypeInfo";

import { Plan_TYPE } from "../plan/PlanTypeInfo";
import { StoreCategory_TYPE } from "./StoreCategoryTypeInfo";
import StoreOrder from "./StoreOrder";
import { StoreOrder_TYPE } from "./StoreOrderTypeInfo";
import { StorePhase_TYPE } from "./StorePhaseTypeInfo";

export const title = "Admin view of: Store order";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import StoreOrderEditor from "./StoreOrderEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = StoreOrder.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "STRING": STRING,
    "FormDef": FormDef_TYPE,
    "StoreCategory": StoreCategory_TYPE,
    "StorePhase": StorePhase_TYPE,
    "StoreOrder": StoreOrder_TYPE,
    "Plan": Plan_TYPE,
    "Organization": Organization_TYPE,
    "OrgUnit": OrgUnit_TYPE,
    "Person": Person_TYPE,
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
        </template>
        <template #preview>
            <StoreOrderEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <StoreOrderEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
