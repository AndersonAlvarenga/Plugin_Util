var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'Plugin_Util', 'coolMethod', [arg0]);
};
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
