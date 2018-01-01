package com.daniel.account.config

import app.auhentication.SocialOAuth
import app.entities.project.ProjectController
import app.entities.project.ProjectRepository
import app.entities.project.ProjectResolver
import app.entities.user.UserController
import app.entities.user.UserRepository
import app.entities.user.UserResolver
import app.graphql.QueryResolver
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class Config {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var projectRepository: ProjectRepository
    @Autowired
    lateinit var userController: UserController
    @Autowired
    lateinit var projectController: ProjectController
    @Autowired
    lateinit var userResolver: UserResolver
    @Autowired
    lateinit var projectResolver: ProjectResolver
    @Autowired
    lateinit var queryResolver: QueryResolver


    @Bean
    @Primary
    fun userRepository(): UserRepository {
        return Mockito.mock(UserRepository::class.java)
    }

    @Bean
    @Primary
    fun projectRepository(): ProjectRepository {
        return Mockito.mock(ProjectRepository::class.java)
    }


    @Bean
    @Primary
    fun userController(): UserController {
        return UserController(userRepository)
    }

    @Bean
    @Primary
    fun projectController(): ProjectController {
        return ProjectController(projectRepository)
    }

    @Bean
    @Primary
    fun userResolver(): UserResolver {
        return UserResolver(userController, projectController)
    }

    @Bean
    @Primary
    fun projectResolver(): ProjectResolver {
        return ProjectResolver(projectController, userController)
    }

    @Bean
    @Primary
    fun queryResolver(): QueryResolver {
        return QueryResolver(userController, projectController)
    }

    @Bean
    @Primary
    fun socialOAuth(): SocialOAuth {
        return SocialOAuth()
    }

}