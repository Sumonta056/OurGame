package extra;

public class Collision {
    public float objectX, objectY; // for storing object's x and y-axis
    public float objectWidth, objectHeight; // for storing object's height and width

    /*
    default constructor.
    when it will be called it will store the object's axis and dimensions
     */
    public Collision(float x, float y, float width, float height) {
        objectX = x;
        objectY = y;
        objectWidth = width;
        objectHeight = height;
    }

    /*
    it will update the axis and dimension of object with new value.
     */
    public void update(float x, float y, float width, float height) {
        objectX = x;
        objectY = y;
        objectWidth = width;
        objectHeight = height;
    }

    /*
    this method will check if there is a collision with the objects (which is calling this method and
    which is a parameter of this method).
     */
    public boolean isCollide (Collision col) {
        return objectX < col.objectX + col.objectWidth && objectY < col.objectY + col.objectHeight &&
                col.objectX < objectX + objectWidth && col.objectY < objectY + objectHeight;
    }
}

