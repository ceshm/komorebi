# Komorebi

OpenGL graphics engine using LWJGL3 and GLFW.

## Dependencies
Included in the build.gradle file:
- LWJGL Minimal OpenGL preset from https://www.lwjgl.org/customize:
    - LWJGL core
    - Assimp
    - GLWF
    - OpenAL & OpenGL
    - stb
    - [JOML](https://github.com/JOML-CI/JOML) (addon option)
- [l33tlabs' pngdecoder](https://github.com/iamtakingiteasy/org.l33tlabs.twl.pngdecoder)


## Testing

#### Main game loop
engineTester > Main

#### Camera movements
The camera class is configured for this key input:
- W, S: z axis
- A, D: x axis
- Space, Tab: y axis



## Credit

The design is based on [ThinMatrix's](https://www.youtube.com/user/ThinMatrix) tutorials.
Modifications have been made for the newest versions of LWJGL and the addition of GLFW 
for handling window and keyboard related events. 
Further changes in the design are expected.
