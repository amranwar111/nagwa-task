package com.team.nagwa.ui

import android.content.Context
import com.team.entities.videos.Video
import java.io.File


val Dummy: List<Video> = mutableListOf(
    Video(
        id = 1,
        type = "VIDEO",
        url = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
        name = "Video 1",
    ),
    Video(
        id = 2,
        type = "VIDEO",
        url = "https://bestvpn.org/html5demos/assets/dizzy.mp4",
        name = "Video 2",
    ),
    Video(
        id = 3,
        type = "PDF",
        url = "https://kotlinlang.org/docs/kotlin-reference.pdf",
        name = "PDF 3",
    ),
    Video(
        id = 4,
        type = "VIDEO",
        url = "https://storage.googleapis.com/exoplayer-test-media-1/mp4/frame-counter-one-hour.mp4",
        name = "Video 4",
    ),
    Video(
        id = 5,
        type = "PDF",
        url = "https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf",
        name = "PDF 5",
    ),
    Video(
        id = 6,
        type = "VIDEO",
        url = "https://storage.googleapis.com/exoplayer-test-media-1/mp4/android-screens-10s.mp4",
        name = "Video 6",
    ),
    Video(
        id = 7,
        type = "VIDEO",
        url = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
        name = "Video 7",
    ),
    Video(
        id = 8,
        type = "VIDEO",
        url = "https://storage.googleapis.com/exoplayer-test-media-1/mp4/android-screens-25s.mp4",
        name = "Video 8",
    ),
    Video(
        id = 9,
        type = "PDF",
        url = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
        name = "PDF 9",
    ),
    Video(
        id = 10,
        type = "PDF",
        url = "https://en.unesco.org/inclusivepolicylab/sites/default/files/dummy-pdf_2.pdf",
        name = "PDF 10",
    ),
    Video(
        id = 11,
        type = "VIDEO",
        url = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
        name = "Video 11",
    ),
    Video(
        id = 12,
        type = "VIDEO",
        url = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
        name = "Video 12",
    ),
)

fun createFile(context: Context, filename: String): File {
    val output = File(context.getExternalFilesDir(null), filename)
    output.mkdirs()
    return output
}

fun createItemName(type: String): String {
    return if (type == "VIDEO") {
        ".mp4"
    } else {
        ".pdf"
    }
}