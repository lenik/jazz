<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";
import { Group_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { FormDef_TYPE } from "lily-basic/src/net/bodz/lily/schema/meta/FormDefTypeInfo";

import { ArtifactDoc } from "./ArtifactDoc";
import { Artifact_TYPE } from "./ArtifactTypeInfo";

export const title = "Choose dialog for: Artifact doc";
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
    "Artifact": Artifact_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="ArtifactDoc.TYPE" :typeMap="typeMap" :modal="modal">
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
        <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
