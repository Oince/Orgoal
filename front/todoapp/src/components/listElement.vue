<template>
  <li class="todoElement" v-for="todo in todoList" v-bind:key="todo.text">
    <div class="checkBox">
      <input type="checkbox" v-on:click="OnCheckBoxClick" />
    </div>
    <div>
      {{ todo.text }}
    </div>
    <div class="deleteButton" v-on:click="OnClickDeleteButton">
      <span>삭제</span>
    </div>
  </li>
</template>

<script>
export default {
  name: "todoElement",
  components: {},
  props: {
    text: String,
  },
  methods: {},
};
</script>
