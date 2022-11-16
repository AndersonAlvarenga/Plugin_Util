package plugin.android_ios.Util;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class Plugin_Util extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        if (action.equals("getVersion")) {
            try{
                PackageManager packageManager = this.cordova.getActivity().getPackageManager();
                callbackContext.success(packageManager.getPackageInfo(this.cordova.getActivity().getPackageName(), 0).versionName+ " "+
                        packageManager.getPackageInfo(this.cordova.getActivity().getPackageName(), 0).versionCode);

            }catch (Exception e){
                callbackContext.error("Erro getVersion"+e.getMessage());
            }
            return true;
        }
        if (action.equals("getAppsInstalados")) {
            try{
                PackageManager packageManager= cordova.getActivity().getPackageManager();
                List<ApplicationInfo> list = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
                String values = "";
                for(ApplicationInfo ap:list){
                    values += values == ""?"":" ";
                    values+=ap.packageName;
                }
                callbackContext.success(values);

            }catch (Exception e){
                callbackContext.error("Erro getAppsInstalados"+e.getMessage());
            }
            return true;
        }
        if (action.equals("deleteApp")) {
            //Seta valores recebidos as variaveis de configuração
            try {
                JSONObject params = args.getJSONObject(0);
                String pacote = params.getString("pacote");
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+pacote));
                cordova.getActivity().startActivity(intent);
                callbackContext.success("Aplicativo Deletado");

            }catch (Exception e){
                callbackContext.error("Erro ao deletar aplicativo: "+e.getMessage());
            }

            return true;
        }
        if (action.equals("abrirApp")) {
            JSONObject params = args.getJSONObject(0);
            String packet = params.getString("pacote");
            intent = new Intent(context.getPackageManager().getLaunchIntentForPackage(packet));
            cordova.getActivity().startActivity(intent);
            return true;
        }
        return false;
    }

   
}
