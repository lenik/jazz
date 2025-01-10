<script lang="ts">
import { onMounted, ref } from "vue";

import { BOOLEAN, INT, LONG } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";

import { FabStdTest_TYPE } from "./FabStdTestTypeInfo";
import FabTrackTest from "./FabTrackTest";
import { FabTrack_TYPE } from "./FabTrackTypeInfo";

export const title = "Admin view of: Fab track test";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import FabTrackTestEditor from "./FabTrackTestEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FabTrackTest.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "INT": INT,
    "FabTrack": FabTrack_TYPE,
    "FabStdTest": FabStdTest_TYPE,
    "BOOLEAN": BOOLEAN,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="FabTrack" data-format="label" data-field="track">Track</th>
            <th data-type="FabStdTest" data-format="label" data-field="standard">Standard</th>
            <th data-type="BOOLEAN" data-field="valid">Valid</th>
        </template>
        <template #preview>
            <FabTrackTestEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FabTrackTestEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
