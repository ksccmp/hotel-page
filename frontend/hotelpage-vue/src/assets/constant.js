export default {
    LOGIN: 'login',
    SETDATES: 'setdates',

    GOMAIN: '/main',
    GOREGISTER: '/register',
    GODETAIL: function(id) {
        return '/roomdetail/' + id;
    },
    GOCALC: function(id, form) {
        return '/roomcalc/' + id + "/" + form;
    },
    GOCHECK: '/check',
    GOREQUESTMAIN: '/requestmain'
}