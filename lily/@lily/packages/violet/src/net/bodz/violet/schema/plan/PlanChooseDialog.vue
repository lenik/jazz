<script lang="ts">
import { onMounted, ref } from "vue";

import { DOUBLE, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";
import { Group_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { FormDef_TYPE } from "lily-basic/src/net/bodz/lily/schema/meta/FormDefTypeInfo";

import { Plan } from "./Plan";
import { PlanCategory_TYPE } from "./PlanCategoryTypeInfo";
import { PlanPhase_TYPE } from "./PlanPhaseTypeInfo";

export const title = "Choose dialog for: Plan";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "skel01-dba/src/ui/lily/EntityChooseDialog.vue";

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
    "PlanCategory": PlanCategory_TYPE,
    "PlanPhase": PlanPhase_TYPE,
    "DOUBLE": DOUBLE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="Plan.TYPE" :typeMap="typeMap" :modal="modal">
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
        <th data-type="STRING" data-field="subject">Subject</th>
        <th data-type="User" data-format="label" data-field="op">Op</th>
        <th data-type="STRING" data-field="rawText">Raw Text</th>
        <th data-type="FormDef" data-format="label" data-field="form">Form</th>
        <th data-type="STRING" data-field="formArguments">Form Arguments</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="PlanCategory" data-format="label" data-field="category">Category</th>
        <th data-type="PlanPhase" data-format="label" data-field="phase">Phase</th>
        <th data-type="INT" data-field="readCount">Read Count</th>
        <th data-type="INT" data-field="voteCount">Vote Count</th>
        <th data-type="INT" data-field="nlike">Nlike</th>
        <th data-type="DOUBLE" data-field="value">Value</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
