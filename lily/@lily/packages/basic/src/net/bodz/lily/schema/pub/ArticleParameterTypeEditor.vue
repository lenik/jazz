<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import GroupChooseDialog from "../account/GroupChooseDialog.vue";
import UserChooseDialog from "../account/UserChooseDialog.vue";
import type { ArticleParameterType } from "./ArticleParameterType";

export interface Props {
}
</script>

<script setup lang="ts">
defineOptions({
    inheritAttrs: false
});

const model = defineModel<%s>();Person

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArticleParameterType.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <FieldGroup decl="net.bodz.lily.concrete.StructRow">
            <FieldRow v-bind="fieldRowProps" :property="meta.creationDate" v-model="model.creationDate">
                <input type="date" v-model="model.creationDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.lastModifiedDate" v-model="model.lastModifiedDate">
                <input type="date" v-model="model.lastModifiedDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.version" v-model="model.version">
                <input type="number" v-model="model.version" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoObject">
            <FieldRow v-bind="fieldRowProps" :property="meta.accessMode" v-model="model.accessMode">
                <input type="number" v-model="model.accessMode" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.acl" v-model="model.acl">
                <input type="number" v-model="model.acl" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.priority" v-model="model.priority">
                <input type="number" v-model="model.priority" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.flags" v-model="model.flags">
                <input type="number" v-model="model.flags" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.state" v-model="model.state">
                <input type="number" v-model="model.state" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.ownerUser" v-model="model.ownerUser">
                <RefEditor :dialog="userChooseDialog" v-model="model.ownerUserId" v-model:id="model.ownerUserId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.ownerGroup" v-model="model.ownerGroup">
                <RefEditor :dialog="groupChooseDialog" v-model="model.ownerGroupId" v-model:id="model.ownerGroupId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoEntity">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.IdEntity">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoNode">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoCode">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoParameter">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.pub._ArticleParameterType_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.name" v-model="model.name">
                <input type="text" v-model="model.name" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.label" v-model="model.label">
                <input type="text" v-model="model.label" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.description" v-model="model.description">
                <input type="text" v-model="model.description" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.dummy" v-model="model.dummy">
                <input type="number" v-model="model.dummy" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.pub.ArticleParameterType">
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
