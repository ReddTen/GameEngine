package Utilities.Math;

public class Matrix4x4 {
    private float[][] matrix;

    // Constructor for Matrix4x4
    public Matrix4x4() {
        matrix = new float[4][4];
    }

    // Set this matrix to the identity matrix
    public void loadIdentity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = (i == j) ? 1.0f : 0.0f;
            }
        }
    }

    // Multiply this matrix by another matrix
    public void multiply(Matrix4x4 other) {
        float[][] result = new float[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }

        this.matrix = result;
    }

    // Create a translation matrix
    public static Matrix4x4 createTranslation(float x, float y, float z) {
        Matrix4x4 result = new Matrix4x4();
        result.loadIdentity();
        result.matrix[0][3] = x;
        result.matrix[1][3] = y;
        result.matrix[2][3] = z;
        return result;
    }

    // Create a scaling matrix
    public static Matrix4x4 createScaling(float x, float y, float z) {
        Matrix4x4 result = new Matrix4x4();
        result.loadIdentity();
        result.matrix[0][0] = x;
        result.matrix[1][1] = y;
        result.matrix[2][2] = z;
        return result;
    }

    // Create a rotation matrix around the X axis
    public static Matrix4x4 createRotationX(float angle) {
        Matrix4x4 result = new Matrix4x4();
        result.loadIdentity();
        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);

        result.matrix[1][1] = cos;
        result.matrix[1][2] = -sin;
        result.matrix[2][1] = sin;
        result.matrix[2][2] = cos;

        return result;
    }

    public static Matrix4x4 createRotationY(float angle) {
        Matrix4x4 result = new Matrix4x4();
        result.loadIdentity();
        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);

        result.matrix[0][0] = cos;
        result.matrix[0][2] = sin;
        result.matrix[2][0] = -sin;
        result.matrix[2][2] = cos;

        return result;
    }

    public static Matrix4x4 createRotationZ(float angle) {
        Matrix4x4 result = new Matrix4x4();
        result.loadIdentity();
        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);

        result.matrix[0][0] = cos;
        result.matrix[0][1] = -sin;
        result.matrix[1][0] = sin;
        result.matrix[1][1] = cos;

        return result;
    }


    // Additional methods to create rotation matrices for Y and Z axes, and other utilities
    // LookAt method
    public static Matrix4x4 lookAt(Vector3D eye, Vector3D center, Vector3D up) {
        Vector3D f = Vector3D.normalize(Vector3D.subtract(center, eye)); // forward
        Vector3D s = Vector3D.normalize(Vector3D.cross(f, up)); // right
        Vector3D u = Vector3D.cross(s, f); // up

        Matrix4x4 result = new Matrix4x4();
        result.loadIdentity();

        result.matrix[0][0] = s.x;
        result.matrix[1][0] = s.y;
        result.matrix[2][0] = s.z;
        result.matrix[0][1] = u.x;
        result.matrix[1][1] = u.y;
        result.matrix[2][1] = u.z;
        result.matrix[0][2] = -f.x;
        result.matrix[1][2] = -f.y;
        result.matrix[2][2] = -f.z;
        result.matrix[3][0] = -Vector3D.dot(s, eye);
        result.matrix[3][1] = -Vector3D.dot(u, eye);
        result.matrix[3][2] = Vector3D.dot(f, eye);

        return result;
    }

    public float determinant() {
        float det = 0;
        for (int i = 0; i < 4; i++) {
            det += (i % 2 == 0 ? 1 : -1) * matrix[0][i] * subDeterminant(0, i);
        }
        return det;
    }

    private float subDeterminant(int row, int col) {
        float[][] subMatrix = new float[3][3];
        int subi = 0;
        for (int i = 0; i < 4; i++) {
            if (i == row) continue;
            int subj = 0;
            for (int j = 0; j < 4; j++) {
                if (j == col) continue;
                subMatrix[subi][subj] = matrix[i][j];
                subj++;
            }
            subi++;
        }
        // Calculate the determinant of the 3x3 subMatrix
        float det = subMatrix[0][0] * (subMatrix[1][1] * subMatrix[2][2] - subMatrix[2][1] * subMatrix[1][2])
                - subMatrix[0][1] * (subMatrix[1][0] * subMatrix[2][2] - subMatrix[2][0] * subMatrix[1][2])
                + subMatrix[0][2] * (subMatrix[1][0] * subMatrix[2][1] - subMatrix[2][0] * subMatrix[1][1]);
        return det;
    }

    // Inverse method
    public Matrix4x4 inverse() {
        float det = determinant();
        if (det == 0) return null; // Non-invertible matrix

        Matrix4x4 cofactorMatrix = new Matrix4x4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cofactorMatrix.matrix[i][j] = ((i + j) % 2 == 0 ? 1 : -1) * subDeterminant(i, j);
            }
        }

        Matrix4x4 result = cofactorMatrix.transpose();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.matrix[i][j] /= det;
            }
        }

        return result;
    }


    // Transpose method
    public Matrix4x4 transpose() {
        Matrix4x4 result = new Matrix4x4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.matrix[i][j] = this.matrix[j][i];
            }
        }
        return result;
    }


    // Load Perspective method
    public void loadPerspective(float fov, float aspect, float zNear, float zFar) {
        float tanHalfFOV = (float)Math.tan(fov / 2);
        float zRange = zNear - zFar;

        this.loadIdentity();
        this.matrix[0][0] = 1.0f / (tanHalfFOV * aspect);
        this.matrix[1][1] = 1.0f / tanHalfFOV;
        this.matrix[2][2] = (-zNear - zFar) / zRange;
        this.matrix[2][3] = 2 * zFar * zNear / zRange;
        this.matrix[3][2] = 1.0f;
        this.matrix[3][3] = 0.0f;
    }


    // Load Orthographic method
    public void loadOrthographic(float left, float right, float bottom, float top, float near, float far) {
        this.loadIdentity();
        this.matrix[0][0] = 2 / (right - left);
        this.matrix[1][1] = 2 / (top - bottom);
        this.matrix[2][2] = -2 / (far - near);
        this.matrix[3][0] = -(right + left) / (right - left);
        this.matrix[3][1] = -(top + bottom) / (top - bottom);
        this.matrix[3][2] = -(far + near) / (far - near);
    }

    // Getters and setters for the matrix elements

    // Set method
    public void set(int row, int col, float value) {
        matrix[row][col] = value;
    }

    // Get method
    public float get(int row, int col) {
        return matrix[row][col];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (float[] row : matrix) {
            for (float element : row) {
                sb.append(element).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}