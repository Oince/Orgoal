<template>
  <div class="todo">
    <div class="inputContainer">
      <input v-model="InputText" />
      <input type="button" value="추가" v-on:click="OnClickAddButton" />
    </div>
    <ul>
      <todo-element
        v-for="todo in todoList"
        v-bind:key="todo.text"
        v-bind:text="todo.text"
      />
      <div class="emptyMessage" v-if="todoList.length == 0">
        할 일이 없습니다!
      </div>
    </ul>
  </div>
</template>

<script>
import todoElement from "./listElement.vue";

export default {
  name: "TodoApp",
  props: {
    todoData: Array,
  },
  components: {
    todoElement,
  },
  data() {
    return {
      InputText: "",
      todoList: [{ text: "hi" }, { text: "bye" }],
    };
  },
  methods: {
    OnClickAddButton: function () {
      console.log(this.InputText);
      if (!this.isEmptyStr()) this.AddTodo();
      this.InputText = "";
    },
    isEmptyStr: function () {
      for (var i = 0; i < this.InputText.length; i++) {
        if (this.InputText[i] != " ") return false;
      }
      return true;
    },
    AddTodo: function () {
      this.todoList.push({ text: this.InputText });
      console.log(this.todoList);
    },
  },
};
</script>

<style scoped>
.inputContainer {
  text-align: center;
}

.todoElement {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-content: center;
  list-style: none;
  border: 1px gray solid;
  border-radius: 3px;
  min-height: 100px;
}

.emptyMessage {
  text-align: center;
}
</style>