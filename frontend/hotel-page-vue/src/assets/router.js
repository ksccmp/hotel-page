import VueRouter from 'vue-router';
import main from '../components/main';

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            name: 'main',
            path: '/main',
            component: main,
        }
    ]
})

export default router;