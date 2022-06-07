<template>
  <div class="home-container">
    <div class="new-activity-container">
      <button class="new-activity blue" @click="onClickNewActivityButton">
        신규 액티비티 작성
      </button>
    </div>
    <div class="slider">
      <input
        class="slider-radio"
        type="radio"
        name="slide"
        v-for="slide in slickSlide"
        v-bind:key="slide"
        v-bind:id="slide"
      />
      <ul id="imgholder" class="imgs">
        <li v-for="slide in slickSlide" v-bind:key="slide">
          <img class="slider-image" v-bind:src="slide" />
        </li>
      </ul>
      <div class="bullets">
        <label v-for="slide in slickSlide" v-bind:key="slide" v-bind:for="slide"
          >&nbsp;</label
        >
      </div>
    </div>

    <div class="home-contents-container">
      <h1>새로 등록된 액티비티</h1>
      <activitylist :list_count_max="5"/>
    </div>
  </div>
</template>
<script>
import { onMounted, onUnmounted, computed } from "vue";
import { useStore } from "vuex";
import activitylist from "./ActivityList.vue";

export default {
  name: "HomePage",
  components: {
    activitylist,
  },
  setup: function () {
    const store = useStore(); // 훅을 사용하여 vuex store 호출
    const isSignedin = computed(() => store.getters["signin/hasToken"]);
    // data
    let slickSlide = [
      "https://t1.daumcdn.net/cfile/tistory/2510CF43519356E514",
      "https://t1.daumcdn.net/cfile/tistory/2133004052CC14491C",
      "http://image.newsis.com/2021/05/10/NISI20210510_0000743111_web.jpg",
    ];
    var interval = null;
    // methods
    let onClickNewActivityButton = function () {
      //let router = useRouter();
      if (isSignedin.value) this.$router.push("newActivity");
      else {
        this.$router.push("/signin");
      }
    };
    // lifecycle hooks
    onMounted(() => {
      console.log("mounted, (interval==null):" + (interval == null));
      setTimeout(() => {
        document.querySelectorAll(".slider-radio")[0].checked = true;
      }, 0);
      interval = setInterval(() => {
        let i = 0;
        let slides = document.querySelectorAll(".slider-radio");
        for (var j = 0; j < slides.length; j++)
          if (slides[j].checked == true) i = j;
        i = (i + 1) % slickSlide.length;
        document.querySelectorAll(".slider-radio")[i].checked = true;
      }, 5000);
    });

    onUnmounted(() => {
      console.log("unMounted, (interval==null):" + (interval == null));
      clearInterval(interval);
    });
    return { slickSlide, interval, onClickNewActivityButton };
  },
};
</script>
<style scoped>
.home-container {
  margin: 20px auto;
  width: 800px;
  position: relative;
}
.new-activity-container {
  margin: 10px;
  height: 30px;
  width: 100%;
}
.new-activity {
  position:absolute;
  right: 0px;
}
button {
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
button.blue {
  color: #fff;
  border-color: #0099d2 !important;
  background: #0099d2 !important;
}


.slider {
  width: 800px;
  height: 300px;
  position: relative;
  margin: 0 auto;
  overflow: hidden;
}
.slider input[type="radio"] {
  display: none;
}

ul.imgs {
  padding: 0;
  margin: 0;
}
ul.imgs li {
  position: absolute;
  left: 640px;
  transition-delay: 1s;
  list-style: none;
  padding: 0;
  margin: 0;
}

.bullets {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  bottom: 20px;
  z-index: 2;
}
.bullets label {
  display: inline-block;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.55);
  width: 20px;
  height: 20px;
  cursor: pointer;
}
.slider-image {
  width: 800px;
  height: 300px;
}

.slider
  input[type="radio"]:nth-child(1):checked
  ~ .bullets
  > label:nth-child(1) {
  background-color: #fff;
}
.slider
  input[type="radio"]:nth-child(2):checked
  ~ .bullets
  > label:nth-child(2) {
  background-color: #fff;
}
.slider
  input[type="radio"]:nth-child(3):checked
  ~ .bullets
  > label:nth-child(3) {
  background-color: #fff;
}
.slider
  input[type="radio"]:nth-child(4):checked
  ~ .bullets
  > label:nth-child(4) {
  background-color: #fff;
}

.slider input[type="radio"]:nth-child(1):checked ~ ul.imgs > li:nth-child(1) {
  left: 0;
  transition: 0.5s;
  z-index: 1;
}
.slider input[type="radio"]:nth-child(2):checked ~ ul.imgs > li:nth-child(2) {
  left: 0;
  transition: 0.5s;
  z-index: 1;
}
.slider input[type="radio"]:nth-child(3):checked ~ ul.imgs > li:nth-child(3) {
  left: 0;
  transition: 0.5s;
  z-index: 1;
}
.slider input[type="radio"]:nth-child(4):checked ~ ul.imgs > li:nth-child(4) {
  left: 0;
  transition: 0.5s;
  z-index: 1;
}
</style>
