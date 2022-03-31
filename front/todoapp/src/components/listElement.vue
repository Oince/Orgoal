<template>
  <li class="todoElement">
    <div class="checkBox">
      <input type="checkbox" v-on:click="OnCheckBoxClick" />
    </div>
    <div>
      {{ todo.text }}
    </div>
    <div
      class="deleteButton"
      @click="
        () => {
          $emit('remove', todo.id);
        }
      "
    >
      <span>삭제</span>
    </div>
  </li>
</template>

<script>
export default {
  props: {
    todo: {
      type: Object,
      required: true,
    },
  },
};
</script>
