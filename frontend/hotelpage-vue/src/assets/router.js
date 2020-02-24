import VueRouter from 'vue-router';
import main from '../pages/main';
import register from '../pages/reservation/register';

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            name: 'main',
            path: '/main',
            component: main,
        },
        {
            name: 'register',
            path: '/register',
            component: register,
        },
    ]
})

export default router;