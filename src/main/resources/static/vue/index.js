routes: [
    { path: '/', component: books_list },
]

const router = new VueRouter({
    routes
})

var app = new Vue({
    router,
    el: '#app',
    data: {
        message: 'Hello Vue!'
    },
    methods: {
        btnClick(event) {
            alert(1);
        }
    }
})