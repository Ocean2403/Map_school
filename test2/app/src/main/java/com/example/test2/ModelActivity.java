package com.example.test2;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.core.view.MenuItemCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.andresoviedo.android_3d_model_engine.model.Camera;
import org.andresoviedo.util.android.ContentUtils;

public class ModelActivity extends Activity {

    private static final int REQUEST_CODE_LOAD_TEXTURE = 1000;
    private int paramType;
    private Uri paramUri;
    private boolean immersiveMode = true;
    private float[] backgroundColor = new float[]{0f, 0f, 0f, 1.0f};

    private ModelSurfaceView gLView;

    private SceneLoader scene;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Try to get input parameters
        Bundle b = getIntent().getExtras();
        if (b != null) {
            if (b.getString("uri") != null) {
                this.paramUri = Uri.parse(b.getString("uri"));
            }
            this.paramType = b.getString("type") != null ? Integer.parseInt(b.getString("type")) : -1;
            this.immersiveMode = "true".equalsIgnoreCase(b.getString("immersiveMode"));
            try {
                String[] backgroundColors = b.getString("backgroundColor").split(" ");
                backgroundColor[0] = Float.parseFloat(backgroundColors[0]);
                backgroundColor[1] = Float.parseFloat(backgroundColors[1]);
                backgroundColor[2] = Float.parseFloat(backgroundColors[2]);
                backgroundColor[3] = Float.parseFloat(backgroundColors[3]);
            } catch (Exception ex) {
                // Assuming default background color
            }
        }
        Log.i("Renderer", "Params: uri '" + paramUri + "'");

        handler = new Handler(getMainLooper());

        scene = new SceneLoader(this);
        scene.init();

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        try {
            gLView = new ModelSurfaceView(this);/**************************************/
            setContentView(gLView);/**thiết lập nội dung view*/
        } catch (Exception e) {
            Toast.makeText(this, "Error loading OpenGL view:\n" +e.getMessage(), Toast.LENGTH_LONG).show();
        }

        // TODO: Alert user when there is no multitouch support (2 fingers). He won't be able to rotate or zoom
        ContentUtils.printTouchCapabilities(getPackageManager());

        //setupOnSystemVisibilityChangeListener();

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.model, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Camera camera = scene.getCamera();
        switch (item.getItemId()) {
            case R.id.zoom_in:
                camera.MoveCameraZ(1f);
//                scene.toggleWireframe();
                break;
            case R.id.zoom_out:
//                scene.toggleBoundingBox();
                camera.MoveCameraZ(-1f);
                break;
            case R.id.rotate_right:
//                scene.toggleTextures();
//                camera.translateCamera(1f,0);
                camera.Rotate1(0.1f);
                break;
            case R.id.rotate_left:
//                scene.toggleAnimation();
//                camera.translateCamera(-1f,0);
                camera.Rotate1(-0.1f);
                break;
//            case R.id.model_toggle_collision:
////                scene.toggleCollision();
//                break;
//            case R.id.model_toggle_lights:
////                scene.toggleLighting();
//                break;
//            case R.id.model_toggle_stereoscopic:
//                //scene.toggleStereoscopic();
//                camera.MoveCameraZ(-1);
//                break;
//            case R.id.model_toggle_blending:
////                scene.toggleBlending();
//                camera.MoveCameraZ(1);
//                break;
//            case R.id.model_toggle_immersive:
////                toggleImmersive();
//                break;
//            case R.id.model_load_texture:
//                Intent target = ContentUtils.createGetContentIntent("image/*");
//                Intent intent = Intent.createChooser(target, "Select a file");
//                try {
//                    startActivityForResult(intent, REQUEST_CODE_LOAD_TEXTURE);
//                } catch (ActivityNotFoundException e) {
//                    // The reason for the existence of aFileChooser
//                }
//                break;
        }

//        hideSystemUIDelayed();
        return super.onOptionsItemSelected(item);
    }
    public Uri getParamUri() {
        return paramUri;
    }

    public int getParamType() {
        return paramType;
    }

    public float[] getBackgroundColor() {
        return backgroundColor;
    }

    public SceneLoader getScene() {
        return scene;
    }

    public ModelSurfaceView getGLView() {
        return gLView;
    }
//    public void Left(MenuItem item) {
//        Camera camera = scene.getCamera();
//        camera.Rotate1(-0.1f);
//
//    }
//    public void Right(MenuItem item) {
//        Camera camera = scene.getCamera();
//        camera.Rotate1(0.1f);
//    }
//    public void Zoom_in(MenuItem item) {
//        Camera camera = scene.getCamera();
//        camera.MoveCameraZ(1f);
//    }
//    public void Zoom_out(MenuItem item) {
//        Camera camera = scene.getCamera();
//        camera.MoveCameraZ(-1f);
//    }

//    @Override
//    public boolean onKeyLongPress(int keyCode, KeyEvent event)
//    {
//        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
//            Log.d(TAG, "Long press KEYCODE_VOLUME_UP");
//            return true;
//        }
//        else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
//            Log.d(TAG, "Long press KEYCODE_VOLUME_DOWN");
//            return true;
//        }
//        return super.onKeyLongPress(keyCode, event);
//    }
}