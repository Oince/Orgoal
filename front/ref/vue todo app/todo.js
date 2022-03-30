import axios from 'axios'

var el;

var todoApp = new Vue({

    data() {
        return {
            todoList: [
                { text: '아침먹기' },
                { text: '점심먹기' },
                { text: '저녁먹기' }
            ]
        };
    },
    methods: {
        OnLoad: function () {
            const BASE_URL = 'http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=5dc753fbb35d7e99e7fd80b06a9a18a7';
            axios.get('${BASE_URL}')
                .then(function (response) {
                    console.log(response.data);
                })
        },
        OnCheckBoxClick: function (event) {
            console.log("완료 버튼 눌림");
            console.log(getIndexInSibling(event.srcElement.parentElement.parentElement))
        },
        OnClickDeleteButton: function (event) {
            console.log("삭제 버튼 눌림");
            console.log(event);
            var index = getIndexInSibling(event.srcElement.parentElement.parentElement);
            console.log(index + "번째 todo");
            todoApp.todoList.splice(index, 1);
        },
        OnClickAddButton: function () {
            var input = document.querySelector("#textInput");
            if (!isEmptyStr(input.value))
                todoApp.todoList.push({ text: input.value });
            input.value = "";
        }
    }
})

function getIndexInSibling(el) {
    for (var i = 0; i < el.parentElement.children.length; i++) {
        if (el == el.parentElement.children[i])
            return i;
    }
    return -1;
}

function isEmptyStr(str) {
    for (var i = 0; i < str.length; i++) {
        if (str[i] != " ")
            return false;
    }
    return true;
}

