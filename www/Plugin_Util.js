var exec = require('cordova/exec');


exports.getVersion = function (success, error) {
    exec(success, error, 'Plugin_Util', 'getVersion');
};
exports.getAppsInstalados = function (success, error) {
    exec(success, error, 'Plugin_Util', 'getAppsInstalados');
};
exports.deleteApp = function (params, success, error) {
    exec(success, error, 'Plugin_Util', 'deleteApp', [params]);
};
exports.abrirApp = function (params, success, error) {
    exec(success, error, 'Plugin_Util', 'abrirApp', [params]);
};
exports.backBotton = function (params, success, error) {
    exec(success, error, 'Plugin_Util', 'backBotton', [params]);
};
