<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import PostTag from "./PostTag";
import { _PostTag_stuff_TYPE } from "./_PostTag_stuff_TypeInfo";

export const title = "Editor view of: Post tag";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import PostChooseDialog from "./PostChooseDialog.vue";
import PostTagTypeChooseDialog from "./PostTagTypeChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<PostTag>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = PostTag.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const postChooseDialog = ref<InstanceType<typeof PostChooseDialog>>();
const postTagTypeChooseDialog = ref<InstanceType<typeof PostTagTypeChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_PostTag_stuff_TYPE">
            <FieldRow :property="meta.post" v-model="model.post">
                <RefEditor :dialog="postChooseDialog" v-model="model.post" v-model:id="model.postId" />
            </FieldRow>
            <FieldRow :property="meta.tag" v-model="model.tag">
                <RefEditor :dialog="postTagTypeChooseDialog" v-model="model.tag" v-model:id="model.tagId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <PostChooseDialog ref="postChooseDialog" />
    <PostTagTypeChooseDialog ref="postTagTypeChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
