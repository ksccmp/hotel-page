import VueRouter from 'vue-router';
import main from '../pages/main';
import register from '../pages/reservation/register';
import roomdetail from '../pages/reservation/roomdetail';
import roomcalc from '../pages/reservation/roomcalc';
import check from '../pages/reservation/check';
import requestmain from '../pages/request/requestmain';

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
        {
            name: 'roomdetail',
            path: '/roomdetail/:id',
            props: true,
            component: roomdetail,
        },
        {
            name: 'roomcalc',
            path: '/roomcalc/:id/:form',
            props: true,
            component: roomcalc,
        },
        {
            name: 'check',
            path: '/check',
            component: check,
        },
        {
            name: 'requestmain',
            path: '/requestmain',
            component: requestmain,
        }
    ]
})

export default router;