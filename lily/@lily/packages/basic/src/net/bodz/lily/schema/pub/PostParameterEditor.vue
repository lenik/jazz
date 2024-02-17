<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import PostChooseDialog from "./PostChooseDialog.vue";
import type { PostParameter } from "./PostParameter";
import PostParameterTypeChooseDialog from "./PostParameterTypeChooseDialog.vue";

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

const meta = PostParameter.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const postChooseDialog = ref<InstanceType<typeof PostChooseDialog>>();
const postParameterTypeChooseDialog = ref<InstanceType<typeof PostParameterTypeChooseDialog>>();
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
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoEntity">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.pub._PostParameter_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.ival" v-model="model.ival">
                <input type="number" v-model="model.ival" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.fval" v-model="model.fval">
                <input type="number" v-model="model.fval" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.sval" v-model="model.sval">
                <input type="text" v-model="model.sval" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.post" v-model="model.post">
                <RefEditor :dialog="postChooseDialog" v-model="model.postId" v-model:id="model.postId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.parameter" v-model="model.parameter">
                <RefEditor :dialog="postParameterTypeChooseDialog" v-model="model.parameterId" v-model:id="model.parameterId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.pub.PostParameter">
        </FieldGroup>
    </div>
    <PostChooseDialog ref="postChooseDialog" />
    <PostParameterTypeChooseDialog ref="postParameterTypeChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
