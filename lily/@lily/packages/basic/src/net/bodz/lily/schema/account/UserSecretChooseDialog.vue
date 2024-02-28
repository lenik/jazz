<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import User from "./User";
import { UserSecret } from "./UserSecret";

export const title = "Choose dialog for: User secret";
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
    "INT": INT,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "User": User.TYPE,
    "STRING": STRING,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="UserSecret.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
        <th data-type="User" data-format="label" data-field="user">The declaring user</th>
        <th data-type="STRING" data-field="password" title="Password data">Password</th>
        <th data-type="STRING" data-field="question" title="Protection question">Question</th>
        <th data-type="STRING" data-field="answer" title="Protection answer">Answer</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
