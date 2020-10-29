import kotlinx.browser.document
import kotlinx.css.*
import react.dom.*
import styled.css
import styled.styledDiv
import styled.styledH1

fun main() {
    render(document.getElementById("root")) {
        child(App::class) {}
    }
}