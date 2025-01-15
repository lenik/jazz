<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";
import { Person_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import { FabTrackParty } from "./FabTrackParty";
import { FabTrack_TYPE } from "./FabTrackTypeInfo";

export const title = "Choose dialog for: Fab track party";
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
    "OffsetDateTime": OffsetDateTime.TYPE,
    "INT": INT,
    "FabTrack": FabTrack_TYPE,
    "Person": Person_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="FabTrackParty.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="FabTrack" data-format="label" data-field="track">Track</th>
        <th data-type="Person" data-format="label" data-field="person">Person</th>
        <th data-type="STRING" data-field="role">Role</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
