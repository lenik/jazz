<script lang="ts">
import { onMounted, ref } from 'vue';

import LocalDate from '@skeljs/core/src/lang/time/LocalDate';
import { INT, STRING } from '@skeljs/core/src/lang/baseinfo';
import { JSON_VARIANT } from '@skeljs/core/src/lang/bas-info';
import Person from './Person';
import { Person_TYPE } from './PersonTypeInfo';
import { DefaultAttachment_TYPE } from './DefaultAttachmentTypeInfo';

export const title = '[Test] Person Admin';

export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from '@skeljs/dba/src/ui/lily/LilyAdmin.vue';
import PersonEditor from './PersonEditor.vue';
import PersonChooseDialog from './PersonChooseDialog.vue';

const props = withDefaults(defineProps<Props>(), {
});

// const admin = ref<InstanceType<typeof LilyAdmin>>();
const defaultPersonChooseDialog = ref<InstanceType<typeof PersonChooseDialog>>();
const type = Person_TYPE;
const selection = ref<any>({});

onMounted(() => {
});

const typeMap = {
    "INT": INT,
    "STRING": STRING,
    "LocalDate": LocalDate.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "Person": Person_TYPE,
    "DefaultAttachment": DefaultAttachment_TYPE,
};

</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">

        <template #columns>
            <th data-field="id" data-type="INT" class="id">ID</th>
            <th data-field="properties" data-type="JSON_VARIANT" class="hidden">Properties</th>
            <th data-field="label" data-type="STRING">Name</th>
            <th data-field="description" data-type="STRING">Description</th>
            <th data-field="gender" data-type="Gender">Gender</th>
            <th data-field="birthday" data-type="LocalDate">Birthday</th>
            <th data-field="father.label" data-type="Person">Father</th>
            <th data-field="mother.label" data-type="Person">Mother</th>
            <th data-field="ssn" data-type="STRING">Social ID</th>
            <th data-field="dln" data-type="STRING">#Driver</th>
        </template>

        <template #preview>
            <PersonEditor class="editor" v-model="selection" />
        </template>

        <template #side-tools> Side Tools</template>

        <template #editor>
            <PersonEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {}
</style>
