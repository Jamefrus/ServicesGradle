package su.jfdev.gradle.service

import org.gradle.api.*
import su.jfdev.gradle.service.additional.*
import su.jfdev.gradle.service.implementation.*
import su.jfdev.gradle.service.require.*
import su.jfdev.gradle.service.util.*

class ServicePlugin: Plugin<Project> {
    override fun apply(project: Project) {
        val sourceSets = project.sourceSets
        with(project) {
            addConfigurationDummies("api", "spec", "impl")
            extensions.create("require", ParallelRequireUser::class.java, project)
            extensions.create("services", ImplementDescriber::class.java, sourceSets)
            extensions.create("describe", AdditionalDescriber::class.java, sourceSets)
        }
    }

    fun Project.addConfigurationDummies(vararg dummies: String) = dummies.forEach { dummy ->
        for (scope in scopes) makeConfig(scope, dummy)
    }
}