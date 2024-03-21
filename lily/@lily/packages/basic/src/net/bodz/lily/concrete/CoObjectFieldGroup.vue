<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import CoObject from "./CoObject";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import GroupChooseDialog from "../schema/account/GroupChooseDialog.vue";
import UserChooseDialog from "../schema/account/UserChooseDialog.vue";

const model = defineModel<CoObject>({ required: true });

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();

// methods

defineExpose({});

onMounted(() => {
});
</script>

<template>
    <FieldGroup :type="CoObject.TYPE" ref="rootElement">
        <FieldRow :property="meta.accessMode" v-model="model.accessMode">
            <input type="number" v-model="model.accessMode" />
        </FieldRow>
        <FieldRow :property="meta.acl" v-model="model.acl">
            <input type="number" v-model="model.acl" />
        </FieldRow>
        <FieldRow :property="meta.label" v-model="model.label">
            <input type="text" v-model="model.label" />
        </FieldRow>
        <FieldRow :property="meta.description" v-model="model.description">
            <input type="text" v-model="model.description" />
        </FieldRow>
        <FieldRow :property="meta.icon" v-model="model.icon">
            <input type="text" v-model="model.icon" />
        </FieldRow>
        <FieldRow :property="meta.priority" v-model="model.priority">
            <input type="number" v-model="model.priority" />
        </FieldRow>
        <FieldRow :property="meta.flags" v-model="model.flags">
            <input type="number" v-model="model.flags" />
        </FieldRow>
        <FieldRow :property="meta.state" v-model="model.state">
            <input type="number" v-model="model.state" />
        </FieldRow>
        <FieldRow :property="meta.ownerUser" v-model="model.ownerUser">
            <RefEditor :dialog="userChooseDialog" v-model="model.ownerUser" v-model:id="model.ownerUserId" />
        </FieldRow>
        <FieldRow :property="meta.ownerGroup" v-model="model.ownerGroup">
            <RefEditor :dialog="groupChooseDialog" v-model="model.ownerGroup" v-model:id="model.ownerGroupId" />
        </FieldRow>
    </FieldGroup>
    <UserChooseDialog ref="userChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
