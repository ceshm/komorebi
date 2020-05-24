package utils

import entity.Camera
import org.joml.Matrix4f
import org.joml.Vector3f

fun createTransformMatrix(translation: Vector3f?, rx: Float, ry: Float, rz: Float, scale: Float): Matrix4f {
    val matrix = Matrix4f()
    // no need to set to identity
    matrix.translate(translation, matrix)
    matrix.rotate(Math.toRadians(rx.toDouble()).toFloat(), Vector3f(1f, 0f, 0f), matrix)
    matrix.rotate(Math.toRadians(ry.toDouble()).toFloat(), Vector3f(0f, 1f, 0f), matrix)
    matrix.rotate(Math.toRadians(rz.toDouble()).toFloat(), Vector3f(0f, 0f, 1f), matrix)
    matrix.scale(scale, matrix)
    return matrix
}

fun createViewMatrix(camera: Camera): Matrix4f {
    val viewMatrix = Matrix4f()
    viewMatrix.rotate(Math.toRadians(camera.pitch.toDouble()).toFloat(), Vector3f(1f, 0f, 0f), viewMatrix)
    viewMatrix.rotate(Math.toRadians(camera.yaw.toDouble()).toFloat(), Vector3f(0f, 1f, 0f), viewMatrix)
    val cameraPos = camera.position
    viewMatrix.translate(Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z), viewMatrix)
    return viewMatrix
}