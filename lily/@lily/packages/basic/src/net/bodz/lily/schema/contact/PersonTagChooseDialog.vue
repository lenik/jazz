<script lang="ts">
import { onMounted, ref } from "vue";

import { INT } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { PersonTag } from "./PersonTag";
import { PersonTagType_TYPE } from "./PersonTagTypeTypeInfo";
import { Person_TYPE } from "./PersonTypeInfo";

export const title = "Choose dialog for: Person tag";
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
    "OffsetDateTime": OffsetDateTime.TYPE,
    "Person": Person_TYPE,
    "PersonTagType": PersonTagType_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="PersonTag.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="Person" data-format="label" data-field="person">Person</th>
        <th data-type="PersonTagType" data-format="label" data-field="tag">Tag</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
