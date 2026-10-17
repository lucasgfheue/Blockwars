package com.blockwars.client;

/** Easing helpers shared by the mecha animations. */
public final class MechaAnim {
    private MechaAnim() {}

    public static float clamp01(float x) {
        return Math.max(0.0F, Math.min(1.0F, x));
    }

    /** Smoothstep. */
    public static float ease(float x) {
        x = clamp01(x);
        return x * x * (3.0F - 2.0F * x);
    }

    /** Cubic ease-out. */
    public static float easeOut(float x) {
        x = clamp01(x);
        float i = 1.0F - x;
        return 1.0F - i * i * i;
    }
}
