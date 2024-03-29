package Utilities.Math;

public class Noise {
    private static final int[] permutation = {151,160,137,91,90,15,131,13,201,95,96,53,194,233,7,225,140,36,103,30,69,142, 8,99,37,240,21,10,23,190, 6,148,247,120,234,75,0,26,197,62,94,252,219,203,117, 35,11,32,57,177,33,88,237,149,56,87,174,20,125,136,171,168, 68,175,74,165,71, 134,139,48,27,166,77,146,158,231,83,111,229,122,60,211,133,230,220,105,92,41, 55,46,245,40,244,102,143,54, 65,25,63,161, 1,216,80,73,209,76,132,187,208, 89, 18,169,200,196,135,130,116,188,159,86,164,100,109,198,173,186, 3,64,52,217,226, 250,124,123, 5,202,38,147,118,126,255,82,85,212,207,206,59,227,47,16,58,17,182, 189,28,42,223,183,170,213,119,248,152, 2,44,154,163, 70,221,153,101,155,167, 43,172,9,129,22,39,253,19,98,108,110,79,113,224,232,178,185, 112,104,218,246, 97,228,251,34,242,193,238,210,144,12,191,179,162,241, 81,51,145,235,249,14,239, 107,49,192,214, 31,181,199,106,157,184, 84,204,176,115,121,50,45,127, 4,150, 254,138,236,205,93,222,114,67,29,24,72,243,141,128,195,78,66,215,61,156,180};
    private static final int[] p = new int[512];

    static {
        for (int i = 0; i < 256; i++) {
            p[i] = permutation[i];
            p[i + 256] = permutation[i];
        }
    }

    private static float fade(float t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private static float grad2D(int hash, float x, float y) {
        switch (hash & 0x3) {
            case 0x0: return  x + y;
            case 0x1: return -x + y;
            case 0x2: return  x - y;
            case 0x3: return -x - y;
            default: return 0; // never happens
        }
    }

    private static float grad3D(int hash, float x, float y, float z) {
        switch (hash & 0xF) {
            case 0x0: return  x + y;
            case 0x1: return -x + y;
            case 0x2: return  x - y;
            case 0x3: return -x - y;
            case 0x4: return  x + z;
            case 0x5: return -x + z;
            case 0x6: return  x - z;
            case 0x7: return -x - z;
            case 0x8: return  y + z;
            case 0x9: return -y + z;
            case 0xA: return  y - z;
            case 0xB: return -y - z;
            case 0xC: return  y + x;
            case 0xD: return -y + z;
            case 0xE: return  y - x;
            case 0xF: return -y - z;
            default: return 0; // never happens
        }
    }

    public static float perlinNoise2D(float x, float y) {
        int xi = (int)x & 255;
        int yi = (int)y & 255;
        float xf = x - (int)x;
        float yf = y - (int)y;

        float u = fade(xf);
        float v = fade(yf);

        int aa, ab, ba, bb;
        aa = p[p[xi]+yi];
        ab = p[p[xi]+yi+1];
        ba = p[p[xi+1]+yi];
        bb = p[p[xi+1]+yi+1];

        float x1, x2, y1;
        x1 = lerp(grad2D(aa, xf, yf), grad2D(ba, xf-1, yf), u);
        x2 = lerp(grad2D(ab, xf, yf-1), grad2D(bb, xf-1, yf-1), u);
        y1 = lerp(x1, x2, v);

        return (y1 + 1) / 2;
    }

    public static float perlinNoise3D(float x, float y, float z) {
        int xi = (int)x & 255;
        int yi = (int)y & 255;
        int zi = (int)z & 255;
        float xf = x - (int)x;
        float yf = y - (int)y;
        float zf = z - (int)z;
        float u = fade(xf);
        float v = fade(yf);
        float w = fade(zf);

        int aaa, aba, aab, abb, baa, bba, bab, bbb;
        aaa = p[p[p[xi]+yi]+zi];
        aba = p[p[p[xi]+yi+1]+zi];
        aab = p[p[p[xi]+yi]+zi+1];
        abb = p[p[p[xi]+yi+1]+zi+1];
        baa = p[p[p[xi+1]+yi]+zi];
        bba = p[p[p[xi+1]+yi+1]+zi];
        bab = p[p[p[xi+1]+yi]+zi+1];
        bbb = p[p[p[xi+1]+yi+1]+zi+1];

        float x1, x2, y1, y2;
        x1 = lerp(grad3D(aaa, xf, yf, zf), grad3D(baa, xf - 1, yf, zf), u);
        x2 = lerp(grad3D(aba, xf, yf - 1, zf), grad3D(bba, xf - 1, yf - 1, zf), u);
        y1 = lerp(x1, x2, v);

        x1 = lerp(grad3D(aab, xf, yf, zf - 1), grad3D(bab, xf - 1, yf, zf - 1), u);
        x2 = lerp(grad3D(abb, xf, yf - 1, zf - 1), grad3D(bbb, xf - 1, yf - 1, zf - 1), u);
        y2 = lerp(x1, x2, v);

        return (lerp(y1, y2, w) + 1) / 2;
    }

    private static float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }
}