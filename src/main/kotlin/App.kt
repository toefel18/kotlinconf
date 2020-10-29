import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h3
import react.dom.img
import styled.css
import styled.styledDiv
import styled.styledH1
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*


external interface AppState : RState {
    var currentVideo: Video?
    var unwatchedVideos: List<Video>
    var watchedVideos: List<Video>
}


class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        unwatchedVideos = listOf(
            Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
            Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
            Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
        )
        watchedVideos = listOf(
            Video(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
        )
    }

    override fun RBuilder.render() {
        styledH1 {
            css {
                fontFamily = "sans-serif"
            }
            +"KotlinConf Explorer"
        }

        div {
            h3 { +"Videos to watch" }
            child(VideoList::class) {
                attrs.videos = state.unwatchedVideos
                attrs.selectedVideo = state.currentVideo
                attrs.onSelectVideo = { video -> setState { currentVideo = video } }
            }
            h3 { +"Videos watched" }
            child(VideoList::class) {
                attrs.videos = state.watchedVideos
                attrs.selectedVideo = state.currentVideo
                attrs.onSelectVideo = { video -> setState { currentVideo = video } }
            }
        }

        state.currentVideo?.let { currentVideo ->
            videoPlayer {
                video = currentVideo
                unwatchedVideo = state.unwatchedVideos.contains(currentVideo)
                onWatchedButtonPressed = {video ->
                    if (state.watchedVideos.contains(video)) {
                        setState {
                            watchedVideos -= video
                            unwatchedVideos += video
                        }
                    } else {
                        setState {
                            watchedVideos += video
                            unwatchedVideos -= video
                        }
                    }
                }
            }
        }
    }
}

