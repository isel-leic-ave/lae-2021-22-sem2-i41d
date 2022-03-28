package pt.isel

// Annotation not aplicable to target class
// @Fancy
class Foo(@Fancy("ola") val dummy: Int) {
    @Fancy("isel") fun bar(){
    }
}
