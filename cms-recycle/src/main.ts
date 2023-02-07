import { createApp } from 'vue'
import App from './App.vue'
import 'normalize.css'
import './assets/css/index.less'
import router from './router/index'
import icon from './global/register-icons'
import pinia from './store'

const app = createApp(App)
app.use(icon)
app.use(pinia)
app.use(router)
app.mount('#app')
