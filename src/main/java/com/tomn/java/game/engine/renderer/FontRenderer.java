package com.tomn.java.game.engine.renderer;

import com.tomn.java.game.engine.Component;
import com.tomn.java.game.engine.renderer.SpriteRenderer;

public class FontRenderer extends Component {

    @Override
    public void start() {
        if(gameObject.getComponent(SpriteRenderer.class) != null) {
            System.out.println("Found font renderer!");
        }
    }

    @Override
    public void update(float dt) {

    }
}
