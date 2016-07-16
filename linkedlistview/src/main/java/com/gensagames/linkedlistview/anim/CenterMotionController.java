package com.gensagames.linkedlistview.anim;


/**
 * Created by Genka on 16.07.2016.
 * GensaGames
 */
public abstract class CenterMotionController extends ScaleCenterController {

    public CenterMotionController() {
    }

    public CenterMotionController(double maxCenterScale, double minSideScale) {
        super(maxCenterScale, minSideScale);
    }

    public CenterMotionController(double maxCenterScale, double minSideScale, double deltaScaleView) {
        super(maxCenterScale, minSideScale, deltaScaleView);
    }
}
