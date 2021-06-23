Vue.component('navbar', {
    data: function () {
        return {
            mock: 0
        }
    },
    template: `
          <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav">
                <li>
                  <img src="../autmun-logo.jpg" height="40px" width="30px"/>
                </li>
                <li class="nav-item">
<!--                  <router-link to="/foo">Go to Foo</router-link>-->
                  <a class="nav-link" href="/" style="font-weight: bold;">Spring Books Store</a>
                </li>
                <li class="nav-item">
<!--                  <router-link to="/bar">Go to Bar</router-link>-->
                  <a class="nav-link" href="/books">Каталог</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#/cart">Корзина</a>
                </li>
              </ul>
            </div>
          </nav>
    `
})