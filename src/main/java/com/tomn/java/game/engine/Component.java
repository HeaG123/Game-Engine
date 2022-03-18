package com.tomn.java.game.engine;

import com.tomn.java.game.engine.go.GameObject;

public abstract class Component {

    public GameObject gameObject = null;

    public void start() {

    }

    public abstract void update(float dt);
}
