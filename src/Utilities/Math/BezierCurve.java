package Utilities.Math;

public class BezierCurve {
    private Vector2D[] controlPoints;

    // Constructor for Bezier curves with variable number of control points
    public BezierCurve(Vector2D... controlPoints) {
        if (controlPoints.length < 2 || controlPoints.length > 4) {
            throw new IllegalArgumentException("BezierCurve must have 2 to 4 control points");
        }
        this.controlPoints = controlPoints;
    }

    // Calculate a point on the curve given a parameter t
    public Vector2D getPoint(float t) {
        if (controlPoints.length == 2) {
            return getLinearBezierPoint(controlPoints[0], controlPoints[1], t);
        } else if (controlPoints.length == 3) {
            return getQuadraticBezierPoint(controlPoints[0], controlPoints[1], controlPoints[2], t);
        } else {
            return getCubicBezierPoint(controlPoints[0], controlPoints[1], controlPoints[2], controlPoints[3], t);
        }
    }

    // Linear Bezier curve (a straight line)
    private Vector2D getLinearBezierPoint(Vector2D p0, Vector2D p1, float t) {
        return new Vector2D(
                (1 - t) * p0.getX() + t * p1.getX(),
                (1 - t) * p0.getY() + t * p1.getY()
        );
    }

    // Quadratic Bezier curve
    private Vector2D getQuadraticBezierPoint(Vector2D p0, Vector2D p1, Vector2D p2, float t) {
        float oneMinusT = 1 - t;
        return new Vector2D(
                oneMinusT * oneMinusT * p0.getX() + 2 * oneMinusT * t * p1.getX() + t * t * p2.getX(),
                oneMinusT * oneMinusT * p0.getY() + 2 * oneMinusT * t * p1.getY() + t * t * p2.getY()
        );
    }

    // Cubic Bezier curve
    private Vector2D getCubicBezierPoint(Vector2D p0, Vector2D p1, Vector2D p2, Vector2D p3, float t) {
        float oneMinusT = 1 - t;
        return new Vector2D(
                oneMinusT * oneMinusT * oneMinusT * p0.getX() + 3 * oneMinusT * oneMinusT * t * p1.getX() +
                        3 * oneMinusT * t * t * p2.getX() + t * t * t * p3.getX(),
                oneMinusT * oneMinusT * oneMinusT * p0.getY() + 3 * oneMinusT * oneMinusT * t * p1.getY() +
                        3 * oneMinusT * t * t * p2.getY() + t * t * t * p3.getY()
        );
    }
}