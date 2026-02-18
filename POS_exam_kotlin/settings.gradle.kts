rootProject.name = "POS_exam_kotlin"

include(
    "Aufgabe1_ORMapping",
    "Aufgabe2_BusinessService",
    "Aufgabe3_RestfulApi"
)

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("1.0.0")
}
