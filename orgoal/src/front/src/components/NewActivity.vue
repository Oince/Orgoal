<template>
  <div class="newactivity">
    <div class="newactivityHeader">
      <div class="newactivityTop">
        <h2 class="title">Create Activity</h2>
        <p class="buttons">
          <button @click.prevent="createNewActivity()" class="button blue">
            등록
          </button>
          <button @click.prevent="doCancel()" class="button">취소</button>
        </p>
      </div>
    </div>
    <form class="newactivityForm">
      <div>
        <input
          type="text"
          id="titleInput"
          class="input_text"
          ref="titleInput"
          v-model.trim="title"
          placeholder="제목을 입력하세요."
        />
      </div>
      <div>
        <textarea
          type="text"
          id="contentInput"
          class="input_text"
          ref="contentInput"
          v-model.trim="content"
          placeholder="내용을 입력하세요."
        />
      </div>
    </form>
    <p>{{ errorMessage }}</p>
  </div>
</template>

<script>
import { useStore } from "vuex";
import axios from "axios";

export default {
  name: "NewActivity",
  setup: function () {
    // data
    const store = useStore();
    const token = store.state.signin.token;

    let title = "";
    let content = "";
    let errorMessage = "";

    // methods
    const createNewActivity = function () {
      if (this.title == "") {
        alert("제목을 입력하세요.");
        this.$refs.titleInput.focus();
        return;
      } else if (this.content == "") {
        alert("내용을 입력하세요.");
        this.$refs.contentInput.focus();
        return;
      }
      let newactivityInfo = {
        title: this.title,
        content: this.content,
      };
      let config = {
        headers: {
          token: this.token,
        },
      };
      axios
        .post("/activity", newactivityInfo, config)
        .then((res) => {
          alert("새 액티비티가 생성되었습니다!");
          this.$router.push("/activity/" + res.data.aid.toString());
        })
        .catch((err) => {
          console.log(err);
        });
      // store
      //   .dispatch("newactivity/createNewActivity", newactivityInfo, token)
      //   .then(() => {
      //     // 액티비티 작성 완료 후 작성한 액티비티 화면으로
      //     newAid = computed(() => store.getters["newactivity/getNewAid"]);
      //     this.$router.push("/activity/" + newAid.toString());
      //   });
    };

    const doCancel = function () {
      this.$router.push("../");
    };

    return {
      token,
      title,
      content,
      errorMessage,
      createNewActivity,
      doCancel,
    };
  },
};
</script>

<style scoped>
.newactivity {
  width: 800px;
  margin: 20px auto;
}
.newactivityForm {
  width: 400px;
  margin: auto;
}
.newactivityForm div > #titleInput {
  width: 400px;
  font-size: 20px;
  height: 32px;
  margin: 10px 0 10px 0;
}
.newactivityForm div > #contentInput {
  width: 400px;
  font-size: 20px;
  height: 400px;
  margin: 10px 0 10px 0;
}
.buttons {
  position: relative;
  height: 32px;
  margin-top: 20px;
}
.buttons > .button {
  overflow: visible;
  cursor: pointer;
  min-width: 125px;
  height: 32px;
  margin: 0 2px;
  padding: 0 15px;
  line-height: 32px;
  font-size: 14px;
  border: 1px solid #dfdfdf;
  background: #fff;
  border-radius: 10px;
}
.buttons > .button.blue {
  color: #fff;
  border-color: #0099d2 !important;
  background: #0099d2 !important;
}
</style>
