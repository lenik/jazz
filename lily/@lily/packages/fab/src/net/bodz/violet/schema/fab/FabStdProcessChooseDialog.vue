<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BOOLEAN, INT, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";
import { Group_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { ArtifactModel_TYPE } from "lily-violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";

import { FabFn_TYPE } from "./FabFnTypeInfo";
import { FabStdProcess } from "./FabStdProcess";
import { FabStdTest_TYPE } from "./FabStdTestTypeInfo";

export const title = "Choose dialog for: Fab std process";
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
    "INT": INT,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "BOOLEAN": BOOLEAN,
    "JSON_VARIANT": JSON_VARIANT,
    "ArtifactModel": ArtifactModel_TYPE,
    "FabFn": FabFn_TYPE,
    "FabStdTest": FabStdTest_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="FabStdProcess.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
        <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
        <th data-type="INT" data-field="accessMode">Access Mode</th>
        <th data-type="INT" data-field="acl">Acl</th>
        <th data-type="STRING" data-field="label">Label</th>
        <th data-type="STRING" data-field="description">Description</th>
        <th data-type="STRING" data-field="icon">Icon</th>
        <th data-type="INT" data-field="priority">Priority</th>
        <th data-type="INT" data-field="flags">Flags</th>
        <th data-type="DefaultState" data-field="state">State</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="BOOLEAN" data-field="valid">Valid</th>
        <th data-type="OffsetDateTime" data-field="validSince">Valid Since</th>
        <th data-type="OffsetDateTime" data-field="validUntil">Valid Until</th>
        <th class="hidden" data-type="JSON_VARIANT" data-field="properties">Properties</th>
        <th class="hidden" data-type="JSON_VARIANT" data-field="files">Files</th>
        <th data-type="ArtifactModel" data-format="label" data-field="output">Output</th>
        <th data-type="FabFn" data-format="label" data-field="function">Function</th>
        <th data-type="INT" data-field="duration">Duration</th>
        <th data-type="BOOLEAN" data-field="strict">Strict</th>
        <th data-type="FabStdTest" data-format="label" data-field="test">Test</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
