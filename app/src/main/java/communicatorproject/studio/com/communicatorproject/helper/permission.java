package communicatorproject.studio.com.communicatorproject.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class permission {

    public static boolean checkPermission (int requestCode, Activity activity, String[] permissions){

        if(Build.VERSION.SDK_INT >= 23){

            List<String> granted = new ArrayList<String>();

            //go through permissions
            for (String perm : permissions){

                boolean validPermission = ContextCompat.checkSelfPermission(activity, perm) == PackageManager.PERMISSION_GRANTED;

                if(!validPermission) granted.add(perm);

                if(granted.isEmpty()) return true;

                String[] newPerm = new String[granted.size()];

                granted.toArray(newPerm);

                ActivityCompat.requestPermissions(activity, newPerm, requestCode);
            }
        }
        return true;
    }
}
