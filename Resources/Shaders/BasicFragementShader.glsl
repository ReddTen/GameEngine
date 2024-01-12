#version 330 core

in vec2 UV;  // UV coordinates from vertex shader

uniform sampler2D textureSampler; // Texture sampler
uniform vec4 baseColor;           // Uniform base color

out vec4 fragColor; // Output color of the pixel

void main() {
    // Simple texture mapping
    fragColor = texture(textureSampler, UV) * baseColor;
    // Or just use a uniform color
    // fragColor = baseColor;
}