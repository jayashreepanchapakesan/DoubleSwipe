package com.example.doubleswipe.;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import android.widget.TextView;

public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gestureDetector;

    private int mActivePointerId0e1, mActivePointerId0e2;
    private int mActivePointerId1e1, mActivePointerId1e2;

    public OnSwipeTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public void onSingleSwipeRightToLeft() {
        System.out.println("It is single swipe right to left");
    }

    public void onSingleSwipeLeftToRight() {
        System.out.println("It is single swipe left to right");
    }

    public void onDoubleSwipeRightToLeft() {
        System.out.println("It is Double swipe right to left");
    }

    public void onDoubleSwipeLeftToRight() {
        System.out.println("It is Double swipe left to right");
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            //Finger 0
            mActivePointerId0e1 = e1.getPointerId(0);
            int pointerIndex0e1 = e1.findPointerIndex(mActivePointerId0e1);
            mActivePointerId0e2 = e2.getPointerId(0);
            int pointerIndex0e2 = e2.findPointerIndex(mActivePointerId0e2);

            //Finger 1
            mActivePointerId1e1 = e1.getPointerId(1);
            int pointerIndex1e1 = e1.findPointerIndex(mActivePointerId1e1);
            mActivePointerId1e2 = e2.getPointerId(1);
            int pointerIndex1e2 = e1.findPointerIndex(mActivePointerId1e2);


            float distanceX0 = e2.getX(pointerIndex0e2) - e1.getX(pointerIndex0e1);
            float distanceY0 = e2.getY(pointerIndex0e2) - e1.getY(pointerIndex0e1);
            float distanceX1 = e2.getX(pointerIndex1e2) - e1.getX(pointerIndex1e1);
            float distanceY1 = e2.getY(pointerIndex1e2) - e1.getY(pointerIndex1e1);


            if (Math.abs(distanceX0) > Math.abs(distanceY0)
                    && Math.abs(distanceX0) > SWIPE_DISTANCE_THRESHOLD) {
                if ( (Math.abs(distanceX1) > Math.abs(distanceY1)
                        && Math.abs(distanceX1) > SWIPE_DISTANCE_THRESHOLD))
                {
                    if( (distanceX0 > 0) && (distanceX1 > 0) )
                        onDoubleSwipeLeftToRight();
                    else if ( (distanceX0 < 0) && (distanceX1 < 0))
                        onDoubleSwipeRightToLeft();
                }else {

                    if (distanceX0 < 0)
                        onSingleSwipeLeftToRight();
                    else
                        onSingleSwipeRightToLeft();
                }
                return true;
            }
            return false;
        }
    }
}