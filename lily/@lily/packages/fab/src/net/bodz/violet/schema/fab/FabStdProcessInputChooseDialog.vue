<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";
import { ArtifactModel_TYPE } from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";
import { Artifact_TYPE } from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactTypeInfo";

import { FabStdProcessInput } from "./FabStdProcessInput";
import { FabStdProcess_TYPE } from "./FabStdProcessTypeInfo";

export const title = "Choose dialog for: Fab std process input";
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
    "OffsetDateTime": OffsetDateTime.TYPE,
    "FabStdProcess": FabStdProcess_TYPE,
    "ArtifactModel": ArtifactModel_TYPE,
    "Artifact": Artifact_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="FabStdProcessInput.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="FabStdProcess" data-format="label" data-field="process">Process</th>
        <th data-type="ArtifactModel" data-format="label" data-field="model">Model</th>
        <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
        <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
