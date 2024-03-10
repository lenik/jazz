<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INET_ADDRESS, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import { TIMESTAMP } from "@skeljs/core/src/lang/time";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import User from "./User";
import { UserRun } from "./UserRun";

export const title = "Choose dialog for: User run";
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
    "User": User.TYPE,
    "STRING": STRING,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "TIMESTAMP": TIMESTAMP,
    "INET_ADDRESS": INET_ADDRESS,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="UserRun.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="User" data-format="label" data-field="user">The user</th>
        <th data-type="STRING" data-field="label">Label</th>
        <th data-type="STRING" data-field="description">Description</th>
        <th data-type="STRING" data-field="icon">Icon</th>
        <th data-type="INT" data-field="priority">Priority</th>
        <th data-type="INT" data-field="flags">Flags</th>
        <th data-type="DefaultState" data-field="state">State</th>
        <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
        <th data-type="INT" data-field="score">Score</th>
        <th data-type="TIMESTAMP" data-field="lastLoginTime" title="Last time of login">Last Login Time</th>
        <th data-type="INET_ADDRESS" data-field="lastLoginIP" title="The source IP of last login">Last Login I P</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
