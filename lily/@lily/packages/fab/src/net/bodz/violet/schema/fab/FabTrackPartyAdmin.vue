<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { Person_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import FabTrackParty from "./FabTrackParty";
import { FabTrack_TYPE } from "./FabTrackTypeInfo";

export const title = "Admin view of: Fab track party";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import FabTrackPartyEditor from "./FabTrackPartyEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FabTrackParty.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "INT": INT,
    "FabTrack": FabTrack_TYPE,
    "Person": Person_TYPE,
    "STRING": STRING,
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
            <th data-type="Person" data-format="label" data-field="person">Person</th>
            <th data-type="STRING" data-field="role">Role</th>
        </template>
        <template #preview>
            <FabTrackPartyEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FabTrackPartyEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
