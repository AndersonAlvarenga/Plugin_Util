package plugin.android_ios.Util;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.util.Log;


import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import android.os.Handler;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * This class echoes a string called from JavaScript.
 */
public class Plugin_Util extends CordovaPlugin {
    private CallbackContext callbackContext;
    private Intent intent = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      
        Context context = cordova.getActivity().getApplicationContext();
        this.callbackContext = callbackContext;
        intent = null;

        if (action.equals("backBotton")) {
            cordova.getActivity().onBackPressed();
            return true;
        }

        if (action.equals("fechaApp")) {
            cordova.getActivity().finish();
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
