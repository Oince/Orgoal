<template>
  <Title msg="hello" />
  <Todo />
</template>

<script>
import Title from "./components/Title.vue";
import Todo from "./components/Todo.vue";

export default {
  name: "App",
  components: {
    Title,
    Todo,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;

  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center;
}
.todo {
  width: 500px;
}
</style>
