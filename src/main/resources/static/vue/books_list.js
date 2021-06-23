Vue.component('books_list', {
    data: function () {
        return {
            booksPage: []
        }
    },
    methods: {
        refreshClick(event) {
            axios.get("http://localhost:8189/store/api/v1/books")
                .then(response => {
                    this.booksPage = response.data;
                });
        }
    },
    created: function () {
        this.refreshClick();
    },
    template: `
        <div>
            <table class="table">
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>Название</td>
                        <td>Цена</td>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="b in booksPage.content">
                        <td>{{b.id}}</td>
                        <td>{{b.title}}</td>
                        <td>{{b.price}}</td>
                    </tr>
                </tbody>
            </table>
            <button @click="refreshClick">Refresh</button>
        </div>
    `
})