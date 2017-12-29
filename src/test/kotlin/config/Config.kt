package config

import app.entities.project.ProjectRepository
import app.entities.project.ProjectResolver
import app.entities.user.UserRepository
import app.entities.user.UserResolver
import app.graphql.QueryResolver
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class Config {

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
    fun userResolver(): UserResolver {
        return Mockito.mock(UserResolver::class.java)
    }

    @Bean
    @Primary
    fun projectResolver(): ProjectResolver {
        return Mockito.mock(ProjectResolver::class.java)
    }

    @Bean
    @Primary
    fun queryResolver(): QueryResolver {
        return Mockito.mock(QueryResolver::class.java)
    }

}