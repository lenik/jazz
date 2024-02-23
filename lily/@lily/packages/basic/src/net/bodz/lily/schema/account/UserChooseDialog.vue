<script lang="ts">
import { Moment } from "moment";
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { User } from "./User";

export const title = "Choose dialog for: User";
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

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

onMounted(() => {
});

</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="User.TYPE" :modal="modal">
        <th data-type="integer" data-field="id">Id</th>
        <th data-type="string" data-format="label" data-field="type">User type like system-user, normal-user, etc.</th>
        <th data-type="string" data-field="name">The user name (unique)</th>
        <th data-type="string" data-field="label">Label</th>
        <th data-type="string" data-field="description">Description</th>
        <th data-type="string" data-field="icon">Icon</th>
        <th data-type="integer" data-field="priority">Priority</th>
        <th data-type="integer" data-field="flags">Flags</th>
        <th data-type="string" data-field="state">State</th>
        <th data-type="Moment" data-field="creationDate">Creation Date</th>
        <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="any" data-field="properties">Properties</th>
        <th data-type="string" data-format="label" data-field="primaryGroup">The primary user group, the default value of ownerGroup.</th>
        <th data-type="string" data-format="label" data-field="referer">The referer user (used for promotion)</th>
        <th data-type="string" data-format="label" data-field="person">Person</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
