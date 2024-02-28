<script lang="ts">
import { onMounted, ref } from "vue";

import type { long } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import FavRecord from "../../concrete/FavRecord";
import IdEntity from "../../concrete/IdEntity";
import PostFav from "./PostFav";
import _PostFav_stuff from "./_PostFav_stuff";

export const title = "Editor view of: Post fav";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import UserChooseDialog from "../account/UserChooseDialog.vue";
import PostChooseDialog from "./PostChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<PostFav>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = PostFav.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const postChooseDialog = ref<InstanceType<typeof PostChooseDialog>>();
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
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="FavRecord.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.user" v-model="model.user">
                <RefEditor :dialog="userChooseDialog" v-model="model.user" v-model:id="model.userId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_PostFav_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.post" v-model="model.post">
                <RefEditor :dialog="postChooseDialog" v-model="model.post" v-model:id="model.postId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <PostChooseDialog ref="postChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
