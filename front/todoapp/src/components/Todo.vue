<template>
  <div class="todo">
    <div class="inputContainer">
      <input v-model="InputText" />
      <input type="button" value="추가" v-on:click="OnClickAddButton" />
    </div>
    <ul v-if="todoList.length">
      <listElement
        v-for="todo in todoList"
        :key="todo.id"
        :todo="todo"
        @remove="RemoveTodo"
      />
    </ul>
    <div class="emptyMessage" v-else>할 일이 없습니다!</div>
  </div>
</template>

<script>
import listElement from "./listElement.vue";

export default {
  name: "TodoApp",
  props: {
    todoData: Array,
  },
  components: {
    listElement,
  },
  data() {
    return {
      InputText: "",
      todoList: [
        { id: 0, text: "hi" },
        { id: 1, text: "bye" },
        { id: 2, text: "??" },
      ],
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
    RemoveTodo: function (id) {
      this.todoList.splice(id, 1);
      for (var i = id; i < this.todoList.length; i++) {
        this.todoList[i].id--;
      }
      //console.log(this.todoList);
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