pluginManagement {
  plugins {
        id("org.jetbrains.kotlin.jvm") version "1.6.10"
    }
}

rootProject.name = "lae-i41d"
include("aula04-reflect")
include("aula05-reflect-instance-and-func-call")
include("aula06-sample-domain")
// include("aula07-logger-custom-getter")
include("aula10-annotations-intro")
// include("aula10-logger-with-formatter")
include("aula16-javapoet")
// include("aula17-logger-dynamic")
// include("aula20-logger-JMH")
include("aula24-logger-dynamic-no-boxing")
include("aula29-sequences")
include("aula32-gc-and-closeable")
include("aula35-closeable-and-cleaner")