package com.example.test2;

import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

import org.andresoviedo.android_3d_model_engine.model.Camera;

public class TouchController {

    private static final String TAG = TouchController.class.getName();

    private static final int TOUCH_STATUS_ZOOMING_CAMERA = 1;
    private static final int TOUCH_STATUS_ROTATING_CAMERA = 4;
    private static final int TOUCH_STATUS_MOVING_WORLD = 5;
    private static final int TOUCH_STATUS_MOVING_CAMERA = 6;

    private final ModelSurfaceView view;
    private final ModelRenderer mRenderer;

    private int pointerCount = 0;
    private float x1 = Float.MIN_VALUE;
    private float y1 = Float.MIN_VALUE;
    private float x2 = Float.MIN_VALUE;
    private float y2 = Float.MIN_VALUE;
    private float dx1 = Float.MIN_VALUE;
    private float dy1 = Float.MIN_VALUE;
    private float dx2 = Float.MIN_VALUE;
    private float dy2 = Float.MIN_VALUE;

    private float length = Float.MIN_VALUE;
    private float previousLength = Float.MIN_VALUE;
    private float currentPress1 = Float.MIN_VALUE;
    private float currentPress2 = Float.MIN_VALUE;

    private float rotation = 0;
    private int currentSquare = Integer.MIN_VALUE;

    private boolean isOneFixedAndOneMoving = false;
    private boolean fingersAreClosing = false;
    private boolean isRotating = false;

   /***/ private boolean isMoving = false;

    private boolean gestureChanged = false;
    private boolean moving = false;
    private boolean simpleTouch = false;
    private long lastActionTime;
    private int touchDelay = -2;
    private int touchStatus = -1;

    private float previousX1;
    private float previousY1;
    private float previousX2;
    private float previousY2;
    private float[] previousVector = new float[4];
    private float[] vector = new float[4];
    private float[] rotationVector = new float[4];
    private float previousRotationSquare;

    public TouchController(ModelSurfaceView view, ModelRenderer renderer) {
        super();
        this.view = view;
        this.mRenderer = renderer;
    }

    public synchronized boolean onTouchEvent(MotionEvent motionEvent) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_UP:   /**Được gọi khi một ngón tay được nhấc lên khỏi màn hình.*/
            case MotionEvent.ACTION_CANCEL:/** Được gọi khi sự kiện chạm bị hủy bỏ hoặc không xác định rõ hành động.*/
            case MotionEvent.ACTION_POINTER_UP:/** Được gọi khi một ngón tay khác nhấc
             lên khỏi màn hình khi đã có nhiều hơn một ngón tay đang chạm.*/
            case MotionEvent.ACTION_HOVER_EXIT:/** Được gọi khi thiết bị sử dụng hệ thống cảm ứng không tiếp xúc
             (ví dụ: bút cảm ứng) không còn tiếp xúc với màn hình.*/
            case MotionEvent.ACTION_OUTSIDE:/**Được gọi khi sự kiện chạm xảy ra bên ngoài khu vực hiển thị của View.*/
                // this to handle "1 simple touch"
                if (lastActionTime > SystemClock.uptimeMillis() - 250) {/**chạm bình thường*/
                    simpleTouch = true;
                } else {/**di chuyển trên màn hình*/
                    gestureChanged = true;
                    touchDelay = 0;
                    lastActionTime = SystemClock.uptimeMillis();
                    simpleTouch = false;
                }
                moving = false;
                break;
            case MotionEvent.ACTION_DOWN:/**Được gọi khi sự kiện chạm xảy ra bên ngoài khu vực hiển thị của View.*/
            case MotionEvent.ACTION_POINTER_DOWN:/**Được gọi khi một ngón tay khác chạm vào màn hình khi đã có một ngón tay đang chạm.*/
            case MotionEvent.ACTION_HOVER_ENTER:/**Được gọi khi một thiết bị sử dụng hệ thống cảm ứng không tiếp xúc (ví dụ: bút cảm ứng) tiếp xúc với màn hình.*/
                Log.d(TAG, "Gesture changed...");
                gestureChanged = true;
                touchDelay = 0;
                lastActionTime = SystemClock.uptimeMillis();
                simpleTouch = false;
                break;
            case MotionEvent.ACTION_MOVE:/** Nó được gọi khi người dùng di chuyển ngón tay trên màn hình cảm ứng mà không nhấc lên*/
                moving = true;
                simpleTouch = false;/**biến moving được gán giá trị true, và biến simpleTouch được gán giá trị false.
             Điều này có thể có ý nghĩa rằng có sự di chuyển đang xảy ra và không phải là một cú chạm đơn giản (simple touch).*/
                touchDelay++;
                break;
            default:
                Log.w(TAG, "Unknown state: " + motionEvent.getAction());
                gestureChanged = true;
        }

        pointerCount = motionEvent.getPointerCount();/**lấy số lượng chạm*/
     /***/   isMoving = moving && !simpleTouch;
        if (pointerCount == 1) {
            x1 = motionEvent.getX();
//            Log.d(TAG, "x1:" + x1 );
            y1 = motionEvent.getY();
//            Log.d(TAG, "y1:" + y1 );
            if (gestureChanged) {/**khi ms chạm không duy chuyển gestureChanged = false
             khi chạm di chuyển và lâu trên 250milis thì gestureChanged = true
             khi thoát ra gestureChanged = true */
//                Log.d(TAG, "x:" + x1 + ",y:" + y1);
                previousX1 = x1;
                previousY1 = y1;
//                Log.d(TAG, "previousX1:" + previousX1 );
//                Log.d(TAG, "previousY1:" + previousY1 );
            }
//            Log.d(TAG, "x1'''''':" + x1 );
//            Log.d(TAG, "y1'''''':" + y1 );
//            Log.d(TAG, "previousX1'''''':" + previousX1 );
//            Log.d(TAG, "previousY1'''''':" + previousY1 );
            dx1 = x1 - previousX1;
            dy1 = y1 - previousY1;
//            Log.d(TAG, "dx1:" + dx1 );
//            Log.d(TAG, "dy1:" + dy1 );
        } else if (pointerCount == 2) {
            x1 = motionEvent.getX(0);
            y1 = motionEvent.getY(0);
            x2 = motionEvent.getX(1);
            y2 = motionEvent.getY(1);
            vector[0] = x2 - x1;
            vector[1] = y2 - y1;
            vector[2] = 0;
            vector[3] = 1;
            float len = Matrix.length(vector[0], vector[1], vector[2]);/**tính độ dài dựa trên hình học không gian 3 chiều*/
            vector[0] /= len;
            vector[1] /= len;

            // Log.d(TAG, "x1:" + x1 + ",y1:" + y1 + ",x2:" + x2 + ",y2:" + y2);
            if (gestureChanged) {
                previousX1 = x1;
                previousY1 = y1;
                previousX2 = x2;
                previousY2 = y2;
                System.arraycopy(vector, 0, previousVector, 0, vector.length);
            }
            dx1 = x1 - previousX1;
            dy1 = y1 - previousY1;
            dx2 = x2 - previousX2;
            dy2 = y2 - previousY2;

            rotationVector[0] = (previousVector[1] * vector[2]) - (previousVector[2] * vector[1]);
            rotationVector[1] = (previousVector[2] * vector[0]) - (previousVector[0] * vector[2]);
            rotationVector[2] = (previousVector[0] * vector[1]) - (previousVector[1] * vector[0]);
            len = Matrix.length(rotationVector[0], rotationVector[1], rotationVector[2]);
            rotationVector[0] /= len;
            rotationVector[1] /= len;
            rotationVector[2] /= len;

            previousLength = (float) Math
                    .sqrt(Math.pow(previousX2 - previousX1, 2) + Math.pow(previousY2 - previousY1, 2));
            length = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

            currentPress1 = motionEvent.getPressure(0);
            currentPress2 = motionEvent.getPressure(1);
            rotation = 0;
            rotation = TouchScreen.getRotation360(motionEvent);
            currentSquare = TouchScreen.getSquare(motionEvent);
            if (currentSquare == 1 && previousRotationSquare == 4) {
                rotation = 0;
            } else if (currentSquare == 4 && previousRotationSquare == 1) {
                rotation = 360;
            }

            // gesture detection
            isOneFixedAndOneMoving = ((dx1 + dy1) == 0) != (((dx2 + dy2) == 0));
            fingersAreClosing = !isOneFixedAndOneMoving && (Math.abs(dx1 + dx2) < 10 && Math.abs(dy1 + dy2) < 10);
            isRotating = !isOneFixedAndOneMoving && (dx1 != 0 && dy1 != 0 && dx2 != 0 && dy2 != 0)
                    && rotationVector[2] != 0;
        }

        if (pointerCount == 1 && simpleTouch) {
            SceneLoader scene = view.getModelActivity().getScene();
            scene.processTouch(x1,y1);
        }
 /**code ban đầu*/
//        int max = Math.max(mRenderer.getWidth(), mRenderer.getHeight());
//        if (touchDelay > 1) {
//            // INFO: Process gesture
//            SceneLoader scene = view.getModelActivity().getScene();
//            scene.processMove(dx1, dy1);
//            Camera camera = scene.getCamera();
//            if (pointerCount == 1 && currentPress1 > 4.0f) {
//            } else if (pointerCount == 1) {
//                touchStatus = TOUCH_STATUS_MOVING_WORLD;
//                // Log.d(TAG, "Translating camera (dx,dy) '" + dx1 + "','" + dy1 + "'...");
//                dx1 = (float)(dx1 / max * Math.PI * 2);
//                dy1 = (float)(dy1 / max * Math.PI * 2);/**dy1 / max * Math.PI * 2 để chuẩn hóa theo đơn vị 1 và ko xoay quá nhiều*/
////                dy1 = 0;
//                camera.translateCamera(dx1,dy1);
//            } else if (pointerCount == 2) {
//                if (fingersAreClosing) {
//                    touchStatus = TOUCH_STATUS_ZOOMING_CAMERA;
//                    float zoomFactor = (length - previousLength) / max * mRenderer.getFar();
//                    Log.i(TAG, "Zooming '" + zoomFactor + "'...");
//                    camera.MoveCameraZ(zoomFactor);
//                }
//                if (isRotating) {
//                    touchStatus = TOUCH_STATUS_ROTATING_CAMERA;
//                    Log.i(TAG, "Rotating camera '" + Math.signum(rotationVector[2]) + "'...");
////                    camera.Rotate((float) (Math.signum(rotationVector[2]) / Math.PI) / 4);
//                    camera.Rotate((float) (0 / 4));
//                }
//            }
//        }
/**code lúc sau*/
        int max = Math.max(mRenderer.getWidth(), mRenderer.getHeight());
        if (touchDelay > 1) {
            // INFO: Process gesture
            SceneLoader scene = view.getModelActivity().getScene();
            scene.processMove(dx1, dy1);
            Camera camera = scene.getCamera();
            if (pointerCount == 1 && currentPress1 > 4.0f) {
                // TODO: Handle the case when only one finger is moving at a high pressure
            } else if (pointerCount == 1) {
                touchStatus = TOUCH_STATUS_MOVING_WORLD;
                // Log.d(TAG, "Translating camera (dx,dy) '" + dx1 + "','" + dy1 + "'...");
                dx1 = (float) (dx1 / max * Math.PI * 2);
                dy1 = (float) (dy1 / max * Math.PI * 2);/**dy1 / max * Math.PI * 2 để chuẩn hóa theo đơn vị 1 và ko xoay quá nhiều*/
//                dy1=0;
                if (Math.abs(dx1) > Math.abs(dy1)) {
                    // Di chuyển trái, phải
                    camera.translateCamera1(4*dx1, 0);
                   /** muốn di chuyển nhanh hơn thì thêm *4*/
                } else {
                    // Di chuyển lên, xuống
                    camera.translateCamera1(0,4* dy1);
                }

            } else if (pointerCount == 2) {

                if (fingersAreClosing) {
                    touchStatus = TOUCH_STATUS_ZOOMING_CAMERA;
                    float zoomFactor = (length - previousLength) / max * mRenderer.getFar();
                    Log.i(TAG, "Zooming '" + zoomFactor + "'...");
                    camera.MoveCameraZ(zoomFactor);
                }
//                if (isRotating) {
//                    touchStatus = TOUCH_STATUS_ROTATING_CAMERA;
//                    Log.i(TAG, "Rotating camera '" + Math.signum(rotationVector[2]) + "'...");
//                    // TODO: Handle camera rotation
////                    camera.Rotate((float) (0 / 4));
////                    camera.Rotate((float) (Math.signum(rotationVector[2]) / Math.PI) / 4);
//                    camera.Rotate((float) (0 / 4));
//                }
                if (Math.abs(dx1) >0 && Math.abs(dx1)>0 && fingersAreClosing==false) {
                    // XOAY trái
                    camera.Rotate1(-0.01f*dx1);
                    /** muốn di chuyển nhanh hơn thì thêm *4*/
                }
                if (Math.abs(dx1) <0 && Math.abs(dx1)<0 && fingersAreClosing==false) {
                    // XOAY phải
                    camera.Rotate1(-0.01f*dx1);

                }

//                if (isMoving) {
//                    touchStatus = TOUCH_STATUS_MOVING_CAMERA;
//
//
//                    if (Math.abs(dx1) > Math.abs(dy1)) {
//                        // Di chuyển trái, phải
//                    dx2 = (float) (dx1 / max * Math.PI * 2);
//                    dy2=0;
//                        camera.translateCamera(1, 0);
//                    } else {
////                    dx2 = 0;
////                    dy2=(float) (dx1 / max * Math.PI * 2);
//                        // Di chuyển lên, xuống
//                        dx2 = (float) (dx1 / max * Math.PI * 2);
//                        dy2=0;
//                        camera.translateCamera(-1, 0);
//                    }
//                }
            }
        }
        previousX1 = x1;
        previousY1 = y1;
        previousX2 = x2;
        previousY2 = y2;

        previousRotationSquare = currentSquare;

        System.arraycopy(vector, 0, previousVector, 0, vector.length);

        if (gestureChanged && touchDelay > 1) {
            gestureChanged = false;
            Log.v(TAG, "Fin");
        }

        view.requestRender();

        return true;

    }
}

class TouchScreen {

    // these matrices will be used to move and zoom image
    private android.graphics.Matrix matrix = new android.graphics.Matrix();
    private android.graphics.Matrix savedMatrix = new android.graphics.Matrix();
    // we can be in one of these 3 states
    private static final int NONE = 0;

    public static float getRotation360(MotionEvent event) {
        double dx = (event.getX(0) - event.getX(1));
        double dy = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(Math.abs(dy), Math.abs(dx));
        double degrees = Math.toDegrees(radians);
        return (float) degrees;
    }

    public static int getSquare(MotionEvent event) {
        double dx = (event.getX(0) - event.getX(1));
        double dy = (event.getY(0) - event.getY(1));
        int square = 1;
        if (dx > 0 && dy == 0) {
            square = 1;
        } else if (dx > 0 && dy < 0) {
            square = 1;
        } else if (dx == 0 && dy < 0) {
            square = 2;
        } else if (dx < 0 && dy < 0) {
            square = 2;
        } else if (dx < 0 && dy == 0) {
            square = 3;
        } else if (dx < 0 && dy > 0) {
            square = 3;
        } else if (dx == 0 && dy > 0) {
            square = 4;
        } else if (dx > 0 && dy > 0) {
            square = 4;
        }
        return square;
    }
}
