#version 330 core

layout(location = 0) in vec3 vertexPosition; // Vertex position (x, y, z)
layout(location = 1) in vec2 vertexUV;       // Vertex texture coordinates (u, v)

uniform mat4 modelMatrix;       // Model transformation matrix
uniform mat4 viewMatrix;        // Camera view matrix
uniform mat4 projectionMatrix;  // Camera projection matrix

out vec2 UV; // Pass UV to fragment shader

void main() {
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(vertexPosition, 1.0);
    UV = vertexUV;
}