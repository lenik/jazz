<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import CourseFav from "./CourseFav";
import { _CourseFav_stuff_TYPE } from "./_CourseFav_stuff_TypeInfo";

export const title = "Editor view of: Course fav";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import FavRecordFieldGroup from "lily-basic/src/net/bodz/lily/concrete/FavRecordFieldGroup.vue";

import CourseChooseDialog from "./CourseChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<CourseFav>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = CourseFav.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const courseChooseDialog = ref<InstanceType<typeof CourseChooseDialog>>();
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
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FavRecordFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_CourseFav_stuff_TYPE">
            <FieldRow :property="meta.course" v-model="model.course">
                <RefEditor :dialog="courseChooseDialog" v-model="model.course" v-model:id="model.courseId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <CourseChooseDialog ref="courseChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
