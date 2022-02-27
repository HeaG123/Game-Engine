package com.tomn.java.game.engine.renderer;

import com.tomn.java.game.engine.Component;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class SpriteRenderer extends Component {

    private Vector4f color;
    private Vector2f texCords;
    private Texture texture;

    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.texCords = null;
    }

    public SpriteRenderer(Texture texture) {
        this.texture = texture;
        this.color = new Vector4f(1,1,1,1);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(float dt) {

    }

    public Vector4f getColor() {
        return this.color;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Vector2f[] getTexCords() {
        Vector2f[] texCoord = {
                new Vector2f(1,1),
                new Vector2f(1,0),
                 new Vector2f(0,0),
                new Vector2f(0,1)
        };

        return texCoord;
    }
}
