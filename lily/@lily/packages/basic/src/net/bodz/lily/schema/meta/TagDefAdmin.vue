<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

import TagDef from "./TagDef";
import TagGroupDef from "./TagGroupDef";

export const title = "Admin view of: Tag def";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import TagDefEditor from "./TagDefEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = TagDef.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "STRING": STRING,
    "int": int.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "TagGroupDef": TagGroupDef.TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="STRING" data-field="label">Label</th>
            <th data-type="STRING" data-field="description">Description</th>
            <th data-type="STRING" data-field="icon">Icon</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="int" data-field="state">State</th>
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="TagGroupDef" data-format="label" data-field="tagGroup">Tag Group</th>
        </template>
        <template #preview>
            <TagDefEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <TagDefEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
