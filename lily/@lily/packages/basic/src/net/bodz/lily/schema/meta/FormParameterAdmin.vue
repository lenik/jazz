<script lang="ts">
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";

import FormParameter from "./FormParameter";

export const title = "Admin view of: Form parameter";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import FormParameterEditor from "./FormParameterEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FormParameter.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="string" data-format="label" data-field="form">Form</th>
            <th data-type="string" data-field="name">Name</th>
            <th data-type="string" data-field="value">Value</th>
        </template>
        <template #preview>
            <FormParameterEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FormParameterEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
