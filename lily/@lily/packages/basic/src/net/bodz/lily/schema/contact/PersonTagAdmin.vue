<script lang="ts">
import { onMounted, ref } from "vue";

import { INT } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";

import PersonTag from "./PersonTag";
import { PersonTagType_TYPE } from "./PersonTagTypeTypeInfo";
import { Person_TYPE } from "./PersonTypeInfo";

export const title = "Admin view of: Person tag";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import PersonTagEditor from "./PersonTagEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PersonTag.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "Person": Person_TYPE,
    "PersonTagType": PersonTagType_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="Person" data-format="label" data-field="person">Person</th>
            <th data-type="PersonTagType" data-format="label" data-field="tag">Tag</th>
        </template>
        <template #preview>
            <PersonTagEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PersonTagEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
