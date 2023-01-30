import { createApp } from 'vue'
import App from './App.vue'
import 'normalize.css'
import './assets/css/index.less'
import router from './router/index'
import store from './store'
import registerIcon from './global/register-icons'

const app = createApp(App)
app.use(registerIcon)
app.use(router)
app.use(store)
app.mount('#app')
